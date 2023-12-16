object GraphGenerator {
    fun generateGraph(dimensionSizes: List<Int>): PGraph {
        val prefix = dimensionSizes.runningFold(1, Int::times).dropLast(1)
        val total = prefix.last() * dimensionSizes.last()
        
        fun forByPrefix(
            i: Int,
            module: (Int) -> Int,
            action: (Int) -> Unit,
        ) {
            for (j in prefix.indices) {
                if ((i / prefix[j]) % dimensionSizes[j] != module(j)) {
                    action(j)
                }
            }
        }
        
        val sizes = IntArray(total)
        
        var rawSize = 0
        repeat(total) { i ->
            val mem = rawSize
            forByPrefix(i, { dimensionSizes[it] - 1 }) { rawSize++ }
            forByPrefix(i, { 0 }) { rawSize++ }
            sizes[i] = rawSize - mem
        }
        
        val res = IntArray(rawSize)
        var pref = 0
        repeat(total) { i ->
            var index = 0
            forByPrefix(i, { dimensionSizes[it] - 1 }) { res[pref + index++] = i + prefix[it] }
            forByPrefix(i, { 0 }) { res[pref + index++] = i - prefix[it] }
            pref += sizes[i]
        }
        
        return PGraph(res, sizes)
    }
}
