object SequenceBFS : DistanceFinder {
    override fun PGraph.findDistances(v: Int): IntArray {
        val res = IntArray(this.size) { -1 }
        val queue = ArrayDeque<Int>()

        res[v] = 0
        queue.addLast(v)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            for (i in 0 until this.toSize(cur)) {
                val to = this.to(cur, i)
                if (res[to] == -1) {
                    res[to] = res[cur] + 1
                    queue.addLast(to)
                }
            }
        }

        return res
    }
}