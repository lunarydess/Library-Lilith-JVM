package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingSine extends AbstractEasing {
    // @formatter:off
    public EasingSine(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingSine(
            final double end,
            final TimeUnit timeUnit,
            final long time
    ) { super(end, timeUnit, time); }
    // @formatter:on

    public @Override double equation(
            final Type type,
            double value
    ) {
        final double x = value / this.getTimeIn(TimeUnit.NANOSECONDS);
        return switch (type) {
            case IN -> 1.0D - Math.cos(x * Math.PI * 0.5D);
            case OUT -> Math.sin(x * Math.PI * 0.5D);
            case INOUT -> -(Math.cos(Math.PI * x) - 1.0D) * 0.5D;
        };
    }
}
