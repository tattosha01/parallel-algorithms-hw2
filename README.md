launch instructions:<br/>
1) edit file src/jmh/kotlin/BFSBenchmark.kt, uncomenting the desired bfs for testing;<br/>
2) from the project root execute the next:<br/>
```./gradlew jmh```

```
Benchmark                 (index)  Mode  Cnt   Score   Error  Units
BFSBenchmark.sequenceBFS        0  avgt    5  20.541 ± 0.273   s/op
BFSBenchmark.sequenceBFS        1  avgt    5  21.325 ± 1.178   s/op
BFSBenchmark.sequenceBFS        2  avgt    5  20.826 ± 0.361   s/op
BFSBenchmark.sequenceBFS        3  avgt    5  20.843 ± 0.806   s/op
BFSBenchmark.sequenceBFS        4  avgt    5  20.561 ± 0.733   s/op

Benchmark                      (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyCAS        0  avgt    5  8.294 ± 0.663   s/op
BFSBenchmark.parallelBFSbyCAS        1  avgt    5  8.325 ± 0.402   s/op
BFSBenchmark.parallelBFSbyCAS        2  avgt    5  8.341 ± 0.393   s/op
BFSBenchmark.parallelBFSbyCAS        3  avgt    5  8.205 ± 1.367   s/op
BFSBenchmark.parallelBFSbyCAS        4  avgt    5  8.321 ± 0.414   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC1        0  avgt    5  6.000 ± 0.692   s/op
BFSBenchmark.parallelBFSbyRAC1        1  avgt    5  6.142 ± 0.766   s/op
BFSBenchmark.parallelBFSbyRAC1        2  avgt    5  5.937 ± 1.012   s/op
BFSBenchmark.parallelBFSbyRAC1        3  avgt    5  6.118 ± 0.457   s/op
BFSBenchmark.parallelBFSbyRAC1        4  avgt    5  6.176 ± 0.546   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC2        0  avgt    5  6.179 ± 0.808   s/op
BFSBenchmark.parallelBFSbyRAC2        1  avgt    5  5.917 ± 0.628   s/op
BFSBenchmark.parallelBFSbyRAC2        2  avgt    5  5.895 ± 0.463   s/op
BFSBenchmark.parallelBFSbyRAC2        3  avgt    5  5.990 ± 0.426   s/op
BFSBenchmark.parallelBFSbyRAC2        4  avgt    5  6.147 ± 0.742   s/op
```
