class PGraph(
    private val g: IntArray,
    private val sz: IntArray,
) {
    private val prefix = sz.runningFold(0, Int::plus)

    val size = sz.size
    fun to(v: Int, i: Int): Int {
        return g[prefix[v] + i]
    }
    fun toSize(v: Int): Int {
        return sz[v]
    }
}