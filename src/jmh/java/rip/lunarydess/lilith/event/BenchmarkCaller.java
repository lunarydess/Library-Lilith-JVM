package rip.lunarydess.lilith.event;


import org.jetbrains.annotations.NotNull;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 4, time = 5)
@Measurement(iterations = 4, time = 5)
public class BenchmarkCaller {

    private static final int ITERATIONS = 100_000;

    private static final EventManager EVENT_MANAGER = new EventManager(HashMap::new, Throwable::printStackTrace);

    @Setup
    public void setup() {
        EVENT_MANAGER.register(BenchmarkEvent.class, new EventHandlers.AbstractHandler<BenchmarkEvent>() {
            @Override
            public void handle(BenchmarkEvent event) {
                event.blackhole.consume(Integer.bitCount(Integer.parseInt("123")));
            }
        });
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public void callBenchmarkListener(Blackhole blackhole) {
        for (int i = 0; i < ITERATIONS; i++) {
            EVENT_MANAGER.call(new BenchmarkEvent(blackhole));
        }
    }

    public class BenchmarkEvent extends AbstractEvent {
        private final Blackhole blackhole;

        public BenchmarkEvent(final Blackhole blackhole) {
            this.blackhole = blackhole;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public boolean equals(Object object) {
            return false;
        }

        @Override
        public @NotNull String getName() {
            return "test";
        }
    }
}