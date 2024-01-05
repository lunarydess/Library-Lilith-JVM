package rip.lunarydess.lilith.math;

import java.util.concurrent.TimeUnit;

public final class Timer {
    private long nanos = System.nanoTime();

    public void updateTime() {
        this.setTimeIn(TimeUnit.NANOSECONDS, System.nanoTime());
    }

    public long elapsedTimeIn(final TimeUnit timeUnit) {
        final long elapsed = System.nanoTime() - this.getTimeIn(TimeUnit.NANOSECONDS);
        return timeUnit == TimeUnit.NANOSECONDS ? elapsed : timeUnit.convert(elapsed, TimeUnit.NANOSECONDS);
    }

    public boolean hasElapsedIn(final TimeUnit timeUnit, final long time) {
        return this.elapsedTimeIn(TimeUnit.NANOSECONDS) >= timeUnit.convert(time, timeUnit);
    }

    public long getTimeIn(final TimeUnit timeUnit) {
        return timeUnit == TimeUnit.NANOSECONDS ? this.nanos : timeUnit.convert(this.nanos, TimeUnit.NANOSECONDS);
    }

    public void setTimeIn(final TimeUnit timeUnit, final long time) {
        if (timeUnit == TimeUnit.NANOSECONDS) this.nanos = time;
        else this.nanos = TimeUnit.NANOSECONDS.convert(time, timeUnit);
    }
}
