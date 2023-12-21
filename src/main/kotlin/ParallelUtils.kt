import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

const val BLOCK_SIZE = 100
const val THREADS_COUNT = 4

@OptIn(DelicateCoroutinesApi::class)
val coroutineBFSDispatcher = newFixedThreadPoolContext(THREADS_COUNT, "ParallelBFSThreadPool") // use for a single in parallel

inline fun scanSerial(
    a: IntArray,
    l: Int = 0,
    r: Int = a.size - 1,
    v: Int = 0,
    itemsScanAction: (Int, Int) -> Int, // potential overflow
) {
    a[l] = itemsScanAction(a[l], v)
    for (i in (l + 1)..r.coerceAtMost(a.size - 1)) {
        a[i] = itemsScanAction(a[i - 1], a[i])
    }
}

inline fun reduceSerial(
    a: IntArray,
    l: Int,
    r: Int,
    itemReduceAction: (Int, Int) -> Int, // potential overflow
): Int {
    var res = a[l]
    for (i in (l + 1)..r.coerceAtMost(a.size - 1)) {
        res = itemReduceAction(res, a[i])
    }
    return res
}

suspend inline fun pfilter(
    a: IntArray,
    l: Int = 0,
    r: Int = a.size - 1,
    crossinline predicate: (Int) -> Boolean,
): IntArray {
    val filtered = IntArray(r - l + 1)
    
    pfor(0, filtered.size - 1) { i ->
        if (predicate(i)) filtered[i] = 1
    }
    
    pscan(filtered, 0, filtered.size - 1, Int::plus)

    val res = IntArray(filtered.last())
    pfor(0, filtered.size - 1) { i ->
        if ((i == 0 && filtered[0] != 0) || (i > 0 && filtered[i - 1] != filtered[i])) {
            res[filtered[i] - 1] = a[i]
        }
    }

    return res
}

suspend fun pscan(
    a: IntArray,
    l: Int = 0,
    r: Int = a.size - 1,
    f: (Int, Int) -> Int // potential overflow
) {
    if (r - l < BLOCK_SIZE) {
        scanSerial(a, l, r, 0, f)
        return
    }

    val sumsSize = (r - l) / BLOCK_SIZE + 1
    val sums = IntArray(sumsSize)

    pfor(0, sumsSize - 1) { iBlock ->
        sums[iBlock] = reduceSerial(a, l + iBlock * BLOCK_SIZE, l + (iBlock + 1) * BLOCK_SIZE - 1, f)
    }

    // pscan(sums, 0, sums.size - 1, f)
    // scanSerial (preferable to pscan on such dimension of task(work) because one frontier is enough small)
    scanSerial(sums, 0, sums.size - 1, 0, f)

    pfor(0, sumsSize - 1) { iBlock ->
        scanSerial(a, l + iBlock * BLOCK_SIZE, l + (iBlock + 1) * BLOCK_SIZE - 1, if (iBlock == 0) 0 else sums[iBlock - 1], f)
    }
}

suspend inline fun pmap(
    a: IntArray,
    l: Int = 0,
    r: Int = a.size - 1,
    crossinline itemTransformAction: (Int) -> Int,
) {
    pfor(l, r) { i ->
        a[i] = itemTransformAction(i)
    }
}

suspend inline fun pfor(
    l: Int,
    r: Int,
    crossinline indexAction: suspend (Int) -> Unit,
) {
    if (r - l < BLOCK_SIZE) {
        for (i in l..r) {
            indexAction(i)
        }
        return
    }

    val blockSize = (r - l + 1) / THREADS_COUNT + 1
    coroutineScope {
        for (i in 0 until THREADS_COUNT) {
            launch { 
                for (j in l + i * blockSize until (l + (i + 1) * blockSize).coerceAtMost(r + 1)) {
                    indexAction(j)
                }
            }
        }
    }
}
