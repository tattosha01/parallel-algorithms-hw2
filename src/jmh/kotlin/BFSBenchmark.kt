package bfs

import PGraph
import ParallelBFSbyCAS
import ParallelBFSbyRAC1
import ParallelBFSbyRAC2
import SequenceBFS
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.Warmup
import org.openjdk.jmh.infra.Blackhole
import java.util.concurrent.TimeUnit

private const val X = 450
private const val Y = 450
private const val Z = 450
private const val COUNT_TEST = 5

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1)
@Warmup(iterations = 1)
@Measurement(iterations = 5)
open class BFSBenchmark {
    private lateinit var g: PGraph
    private lateinit var v: IntArray

    @Param("0", "1", "2", "3", "4")
    var index = 0

    @Setup
    fun setup() {
        g = GraphGenerator.generateGraph(listOf(X, Y, Z))
        v = IntArray(COUNT_TEST) { it * (Z / COUNT_TEST) * X * Y }
    }

    //@Benchmark
    //fun sequenceBFS(blackhole: Blackhole) { 
    //    blackhole.consume(
    //        with(SequenceBFS) { 
    //            g.findDistances(v[index]) 
    //        }
    //    )
    //}

    //@Benchmark
    //fun parallelBFSbyCAS(blackhole: Blackhole) { 
    //    blackhole.consume(
    //        with(ParallelBFSbyCAS) { 
    //            blackhole.consume(g.findDistances(v[index])) 
    //        }
    //    )
    //}

    //@Benchmark
    //fun parallelBFSbyRAC1(blackhole: Blackhole) { 
    //    blackhole.consume(
    //        with(ParallelBFSbyRAC1) { 
    //            blackhole.consume(g.findDistances(v[index])) 
    //        }
    //    )
    //}

    //@Benchmark
    //fun parallelBFSbyRAC2(blackhole: Blackhole) { 
    //    blackhole.consume(
    //        with(ParallelBFSbyRAC2) { 
    //            blackhole.consume(g.findDistances(v[index]))
    //        }
    //    )
    //}
}
