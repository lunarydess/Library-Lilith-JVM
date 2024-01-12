package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingCubic extends AbstractEasing {
    // @formatter:off
    public EasingCubic(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingCubic(
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
        final double x3 = x * x * x;
        return switch (type) {
            case IN -> x3;
            case OUT -> 1.0D - x3;
            case INOUT -> x < 0.5D ? 4.0D * x3 :
                    1.0D - ((-2.0D * x + 2.0D) * (-2.0D * x + 2.0D) * (-2.0D * x + 2.0D)) * 0.5D;
        };
    }
}
