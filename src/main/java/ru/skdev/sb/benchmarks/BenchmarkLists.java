package ru.skdev.sb.benchmarks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * @author sergekos
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Threads(1)
@Warmup(iterations = 2)
@Measurement(iterations = 4)
public class BenchmarkLists {

    @Param({"10000"})
    private int N;
        
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkLists.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        // NOP yet
    }

    private void insertToMiddle(List<Long> list, Blackhole bh) {
        for (int i = 0; i < N; i++) {
            int pos = list.size() / 2;
            list.add(pos, Long.MAX_VALUE);
            bh.consume(pos);
        }
    }

    @Benchmark
    public void arrayList(Blackhole bh) {
        ArrayList<Long> arrayList = new ArrayList<>();
        insertToMiddle(arrayList, bh);
    }

    @Benchmark
    public void linkedList(Blackhole bh) {
        LinkedList<Long> linkedList = new LinkedList<>();
        insertToMiddle(linkedList, bh);
    }
}
