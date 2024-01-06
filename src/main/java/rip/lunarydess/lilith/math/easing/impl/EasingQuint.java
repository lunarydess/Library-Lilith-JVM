package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingQuint extends AbstractEasing {
    // @formatter:off
    public EasingQuint(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingQuint(
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
        final double x5 = x * x * x * x * x;
        return switch (type) {
            case IN -> x5;
            case OUT -> 1.0D - ((1.0D - x) * (1.0D - x) * (1.0D - x) * (1.0D - x) * (1.0D - x));
            case INOUT -> x < 0.5D ? 16.0D * x5 : 1.0D - Math.pow(-2.0D * x + 2.0D, 5.0D) * 0.5D;
        };
    }
}
