package rip.lunarydess.lilith.math.easing;

import rip.lunarydess.lilith.math.Timer;

import java.util.concurrent.TimeUnit;

public abstract class AbstractEasing {
    private final Timer timer;
    private final double end;
    private long nanos;

    // @formatter:off
    public AbstractEasing(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }
    // @formatter:on

    public AbstractEasing(
            final double end,
            final TimeUnit timeUnit,
            final long time
    ) {
        this.nanos = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        this.end = end;
        this.timer = new Timer();
    }

    protected abstract double equation(final Type type, final double value);

    public void reuse() {
        if (!this.isDone()) return;
        final long time = this.getTimeIn(TimeUnit.NANOSECONDS);
        this.getTimer().setTimeIn(TimeUnit.NANOSECONDS, System.nanoTime() - (time - Math.min(time, this.timer.getTimeIn(TimeUnit.NANOSECONDS))));
    }

    public double outputAs(final Type type) {
        return this.equation(type, this.getTimer().elapsedTimeIn(TimeUnit.NANOSECONDS)) * this.end;
    }

    public final long getTimeIn(final TimeUnit timeUnit) {
        return timeUnit == TimeUnit.NANOSECONDS ? this.nanos : timeUnit.convert(this.nanos, TimeUnit.NANOSECONDS);
    }

    public final void setTimeFrom(final TimeUnit timeUnit, final long time) {
        this.nanos = TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }

    public Timer getTimer() {
        return this.timer;
    }

    public boolean isDone() {
        return this.getTimer().hasElapsedIn(TimeUnit.NANOSECONDS, this.getTimeIn(TimeUnit.NANOSECONDS));
    }

    public enum Type {IN, OUT, INOUT}
}
