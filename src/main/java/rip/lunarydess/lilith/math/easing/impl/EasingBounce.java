package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingBounce extends AbstractEasing {
    // @formatter:off
    public EasingBounce(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingBounce(
            final double end,
            final TimeUnit timeUnit,
            final long time
    ) { super(end, timeUnit, time); }
    // @formatter:on

    public @Override double equation(
            final Type type,
            double value
    ) { // @formatter:off
        double x = value / this.getTimeIn(TimeUnit.NANOSECONDS);
        final double n1 = 7.5625D, d1 = 2.75D;
        return x < 1.0D / d1 ? n1 * x * x                            :
               x < 2.0D / d1 ? n1 * (x -= 1.5D  / d1) * x + 0.75D    :
               x < 2.5D / d1 ? n1 * (x -= 2.25D / d1) * x + 0.9375D  :
                               n1 * (x -= 2.625 / d1) * x + 0.984375D;
    } // @formatter:on
}
