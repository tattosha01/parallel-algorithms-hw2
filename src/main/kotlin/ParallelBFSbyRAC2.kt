import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object ParallelBFSbyRAC2 : DistanceFinder {
    override fun PGraph.findDistances(v: Int): IntArray {
        return runBlocking {
            withContext(coroutineBFSDispatcher) {
                findDistances(this@findDistances, v)
            }
        }
    }

    private suspend fun findDistances(
        g: PGraph,
        v: Int,
    ): IntArray {
        val degree = IntArray(D_SIZE)
        val valid = IntArray(g.size)
        val res = IntArray(g.size) { -1 }

        var iter = 0

        var frontier = intArrayOf(v)
        var size = frontier.size

        res[v] = 0

        do {
            pfor(0, size - 1) { i ->
                degree[i] = g.toSize(frontier[i])
            }
            pscan(degree, 0, size - 1, Int::plus)

            val newCapacity = degree[size - 1]
            val newFrontier = IntArray(newCapacity) { -1 }
            val curDistance = res[frontier[0]]

            pfor(0, size - 1) { i ->
                val node = frontier[i]
                val offset = if (i == 0) 0 else degree[i - 1]
                pfor(0, g.toSize(node) - 1) { j ->
                    val to = g.to(node, j)
                    if (res[to] == -1) {
                        res[to] = curDistance + 1
                        valid[to] = offset + j
                        newFrontier[offset + j] = to
                    }
                }
            }

            frontier = pfilter(newFrontier, 0, newCapacity - 1) { i ->
                val node = newFrontier[i]
                node != -1 && valid[node] == i
            }

            size = frontier.size
            iter++
        } while (size > 0)

        return res
    }
}