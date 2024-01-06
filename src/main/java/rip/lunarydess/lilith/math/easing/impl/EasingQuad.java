package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingQuad extends AbstractEasing {
    // @formatter:off
    public EasingQuad(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingQuad(
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
        final double x2 = x * x;
        return switch (type) {
            case IN -> x2;
            case OUT -> 1.0D - ((1.0D - x) * (1.0D - x));
            case INOUT -> x < 0.5D ? 2.0D * x2 : 1.0D - ((-2.0D * x + 2.0D) * (-2.0D * x + 2.0D)) * 0.5D;
        };
    }
}
