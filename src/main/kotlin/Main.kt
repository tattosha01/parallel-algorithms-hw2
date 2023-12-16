import java.text.DecimalFormat
import kotlin.random.Random
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

// 400 400 400 and more -> need -Xmx8192m

const val X = 300
const val Y = 300
const val Z = 300
const val COUNT_TEST = 5
const val D_SIZE = X*Y*2 + X*Z*2 + Y*Z*2

val formatter = { it: Any -> DecimalFormat("0.000").format(it) }

fun main() {
    naiveBenchmark()
}

fun naiveBenchmark() {
    val distanceFinders = listOf(
        SequenceBFS, ParallelBFSbyCAS, ParallelBFSbyRAC1, ParallelBFSbyRAC2
    )
    
    println("Enter the numbers from the set [1, 2, 3, 4], where:\n1 - seq\n2 - cas\n3 - rac_pfor\n4 - rac_if")
    val set = readln().split(' ').map {it.toInt()}.toSet()

    println("Generation of input with size = ($X, $Y, $Z) started...")
    val g = GraphGenerator.generateGraph(listOf(X, Y, Z))
    println("Generation of input with size = ($X, $Y, $Z) finished\n")

    val v = IntArray(COUNT_TEST) { Random.nextInt(0, X * Y * Z) }
    
    val rawResults= distanceFinders.mapIndexed { index, distanceFinder -> 
        if (index + 1 in set) {
            println("WarmingUp for ${distanceFinder::class.simpleName} with size = ($X, $Y, $Z) started...")
            warmingUp(distanceFinder, g)
            Array(COUNT_TEST) { i ->
                println("Running BFS for input[$i], distanceFinder=${distanceFinder::class.simpleName}, from node=${v[i] + 1} out of ${X * Y * Z}...")
                measureTimeAndGetResult(distanceFinder, g, v[i])
            }
        } else {
            arrayOf()
        }
    }
    
    val results = rawResults.filter { it.isNotEmpty() }
    for (i in 0 until COUNT_TEST) {
        for (j in 1 until results.size) {
            check(results[j - 1][i].first contentEquals results[j][i].first) {
                "Error: #test=${i + 1}, content is not eq"
            }
        }
    }
    
    val times = rawResults.map { arr ->
        if (arr.isNotEmpty()) {
            arr.map { it.second }.toDoubleArray()
        } else {
            DoubleArray(COUNT_TEST)
        }
        
    }

    println(distanceFinders.map { it::class.simpleName }.joinToString(" | "))
    repeat(COUNT_TEST) { i ->
        println("${formatter(times[0][i])}s | ${formatter(times[1][i])}s | ${formatter(times[2][i])}s | ${formatter(times[3][i])}s")
    }

    val sum = times.map { 
        it.fold(0.0, Double::plus)
    }
    
    println("avg time (seq) = ${formatter(sum[0] / COUNT_TEST)}s")
    println("avg time (cas) = ${formatter(sum[1] / COUNT_TEST)}s")
    println("avg time (rac_pfor) = ${formatter(sum[2] / COUNT_TEST)}s")
    println("avg time (rac_if) = ${formatter(sum[3] / COUNT_TEST)}s")
}

private fun warmingUp(
    distanceFinder: DistanceFinder,
    g: PGraph,
    v: Int = 0,
) {
    with(distanceFinder) {
        g.findDistances(v)
    }
}

@OptIn(ExperimentalTime::class)
fun measureTimeAndGetResult(
    distanceFinder: DistanceFinder,
    g: PGraph,
    v: Int,
): Pair<IntArray, Double> {
    val res: IntArray
    val time = with(distanceFinder) {
        measureTime {
            res = g.findDistances(v)
        }
    }
    
    return res to time.toDouble(DurationUnit.SECONDS)
}
