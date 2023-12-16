launch from the root:<br/>
```./gradlew jmh```

```
Benchmark                 (index)  Mode  Cnt   Score   Error  Units
BFSBenchmark.sequenceBFS        0  avgt       20.895           s/op
BFSBenchmark.sequenceBFS        1  avgt       21.008           s/op
BFSBenchmark.sequenceBFS        2  avgt       21.102           s/op
BFSBenchmark.sequenceBFS        3  avgt       21.138           s/op
BFSBenchmark.sequenceBFS        4  avgt       21.411           s/op

Benchmark                      (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyCAS        0  avgt       8.428           s/op
BFSBenchmark.parallelBFSbyCAS        1  avgt       8.387           s/op
BFSBenchmark.parallelBFSbyCAS        2  avgt       8.053           s/op
BFSBenchmark.parallelBFSbyCAS        3  avgt       8.087           s/op
BFSBenchmark.parallelBFSbyCAS        4  avgt       8.195           s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC1        0  avgt       5.516           s/op
BFSBenchmark.parallelBFSbyRAC1        1  avgt       5.565           s/op
BFSBenchmark.parallelBFSbyRAC1        2  avgt       5.782           s/op
BFSBenchmark.parallelBFSbyRAC1        3  avgt       5.610           s/op
BFSBenchmark.parallelBFSbyRAC1        4  avgt       5.627           s/op

Benchmark                       (index)  Mode  Cnt  Score   Error  Units
BFSBenchmark.parallelBFSbyRAC2        0  avgt       6.003           s/op
BFSBenchmark.parallelBFSbyRAC2        1  avgt       5.949           s/op
BFSBenchmark.parallelBFSbyRAC2        2  avgt       5.702           s/op
BFSBenchmark.parallelBFSbyRAC2        3  avgt       5.709           s/op
BFSBenchmark.parallelBFSbyRAC2        4  avgt       5.473           s/op
```
