package ru.skdev.sb;

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
import org.openjdk.jmh.results.format.ResultFormatType;
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
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Threads(1)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
public class BenchmarkLists {

    @Param({"50", "100", "200", "400", "800", "1600", "3200", "6400", "12800"})
    private int N;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkLists.class.getSimpleName())
                .result("lists-benchmark.csv")
                .resultFormat(ResultFormatType.CSV)
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

    private void insertToBegin(List<Long> list, Blackhole bh) {
        for (int i = 0; i < N; i++) {
            list.add(0, Long.MAX_VALUE);
            bh.consume(i);
        }
    }

    private void insertToEnd(List<Long> list, Blackhole bh) {
        for (int i = 0; i < N; i++) {
            list.add(Long.MAX_VALUE);
            bh.consume(i);
        }
    }

    @Benchmark
    public void arrayListInsertToBegin(Blackhole bh) {
        List<Long> list = new ArrayList<>();
        insertToBegin(list, bh);
    }

    @Benchmark
    public void linkedListInsertToBegin(Blackhole bh) {
        List<Long> list = new LinkedList<>();
        insertToBegin(list, bh);
    }

    @Benchmark
    public void arrayListInsertToMiddle(Blackhole bh) {
        List<Long> list = new ArrayList<>();
        insertToMiddle(list, bh);
    }

    @Benchmark
    public void linkedListInsertToMiddle(Blackhole bh) {
        List<Long> list = new LinkedList<>();
        insertToMiddle(list, bh);
    }

    @Benchmark
    public void arrayListInsertToEnd(Blackhole bh) {
        List<Long> list = new ArrayList<>();
        insertToEnd(list, bh);
    }

    @Benchmark
    public void linkedListInsertToEnd(Blackhole bh) {
        List<Long> list = new LinkedList<>();
        insertToEnd(list, bh);
    }
}
