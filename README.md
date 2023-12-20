launch instructions:<br/>
1) edit file src/jmh/kotlin/BFSBenchmark.kt, uncomenting the desired bfs for testing;<br/>
2) from the project root execute the next:<br/>
```./gradlew jmh```

power adapter, max-cpu-performance-mode:
```
Benchmark                 (index)  Mode  Cnt   Score   Error  Units
BFSBenchmark.sequenceBFS        0  avgt    5  20.708 ± 0.958   s/op
BFSBenchmark.sequenceBFS        1  avgt    5  20.717 ± 0.297   s/op
BFSBenchmark.sequenceBFS        2  avgt    5  20.879 ± 0.601   s/op
BFSBenchmark.sequenceBFS        3  avgt    5  21.063 ± 1.377   s/op
BFSBenchmark.sequenceBFS        4  avgt    5  20.736 ± 1.809   s/op

Benchmark                      (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyCAS        0  avgt    5  8.711 ± 0.604   s/op
BFSBenchmark.parallelBFSbyCAS        1  avgt    5  8.512 ± 0.344   s/op
BFSBenchmark.parallelBFSbyCAS        2  avgt    5  8.305 ± 0.430   s/op
BFSBenchmark.parallelBFSbyCAS        3  avgt    5  8.351 ± 0.603   s/op
BFSBenchmark.parallelBFSbyCAS        4  avgt    5  8.646 ± 0.777   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC1        0  avgt    5  6.501 ± 0.487   s/op
BFSBenchmark.parallelBFSbyRAC1        1  avgt    5  6.435 ± 0.943   s/op
BFSBenchmark.parallelBFSbyRAC1        2  avgt    5  6.307 ± 0.237   s/op
BFSBenchmark.parallelBFSbyRAC1        3  avgt    5  6.283 ± 0.357   s/op
BFSBenchmark.parallelBFSbyRAC1        4  avgt    5  6.276 ± 0.705   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC2        0  avgt    5  6.621 ± 0.611   s/op
BFSBenchmark.parallelBFSbyRAC2        1  avgt    5  6.121 ± 0.464   s/op
BFSBenchmark.parallelBFSbyRAC2        2  avgt    5  6.179 ± 0.472   s/op
BFSBenchmark.parallelBFSbyRAC2        3  avgt    5  6.212 ± 0.957   s/op
BFSBenchmark.parallelBFSbyRAC2        4  avgt    5  6.298 ± 0.255   s/op
```

battery, safe-energy-mode:
```
Benchmark                 (index)  Mode  Cnt   Score   Error  Units
BFSBenchmark.sequenceBFS        0  avgt    5  32.725 ± 2.357   s/op
BFSBenchmark.sequenceBFS        1  avgt    5  32.190 ± 8.527   s/op
BFSBenchmark.sequenceBFS        2  avgt    5  32.846 ± 1.042   s/op
BFSBenchmark.sequenceBFS        3  avgt    5  32.835 ± 0.994   s/op
BFSBenchmark.sequenceBFS        4  avgt    5  32.435 ± 1.480   s/op

Benchmark                      (index)  Mode  Cnt   Score   Error  Units
BFSBenchmark.parallelBFSbyCAS        0  avgt    5  10.167 ± 3.098   s/op
BFSBenchmark.parallelBFSbyCAS        1  avgt    5  10.229 ± 2.163   s/op
BFSBenchmark.parallelBFSbyCAS        2  avgt    5  10.371 ± 1.553   s/op
BFSBenchmark.parallelBFSbyCAS        3  avgt    5  10.121 ± 1.946   s/op
BFSBenchmark.parallelBFSbyCAS        4  avgt    5   9.872 ± 1.907   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC1        0  avgt    5  8.937 ± 0.709   s/op
BFSBenchmark.parallelBFSbyRAC1        1  avgt    5  8.779 ± 0.858   s/op
BFSBenchmark.parallelBFSbyRAC1        2  avgt    5  8.770 ± 1.120   s/op
BFSBenchmark.parallelBFSbyRAC1        3  avgt    5  8.865 ± 0.759   s/op
BFSBenchmark.parallelBFSbyRAC1        4  avgt    5  9.247 ± 0.660   s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC2        0  avgt    5  8.095 ± 0.640   s/op
BFSBenchmark.parallelBFSbyRAC2        1  avgt    5  8.057 ± 1.617   s/op
BFSBenchmark.parallelBFSbyRAC2        2  avgt    5  8.379 ± 1.039   s/op
BFSBenchmark.parallelBFSbyRAC2        3  avgt    5  7.547 ± 0.649   s/op
BFSBenchmark.parallelBFSbyRAC2        4  avgt    5  7.534 ± 0.353   s/op
```
