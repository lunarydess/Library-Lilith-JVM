package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingBack extends AbstractEasing {
    // @formatter:off
    public EasingBack(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingBack(
            final double end,
            final TimeUnit timeUnit,
            final long time
    ) { super(end, timeUnit, time); }
    // @formatter:on

    public @Override double equation(
            final Type type,
            double value
    ) {
        final double c2 = 2.5949095D;
        final double x = value / this.getTimeIn(TimeUnit.NANOSECONDS);

        return switch (type) {
            case IN -> 2.70158D * Math.pow(x, 3) - 1.70158D * Math.pow(x, 2);
            case OUT -> 1.0D + 2.70158D * Math.pow(x - 1.0D, 3.0D) + 1.70158D * Math.pow(x - 1.0D, 2.0D);
            case INOUT -> x < 0.5 ?
                    Math.pow(2.0D * x, 2.0D) * ((c2 + 1.0D) * 2.0D * x - c2) * 0.5D :
                    (Math.pow(2.0D * x - 2.0D, 2.0D) * ((c2 + 1.0D) * (x * 2.0D - 2.0D) + c2) + 2.0D) * 0.5D;
        };
    }
}
