package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingExpo extends AbstractEasing {
    // @formatter:off
    public EasingExpo(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingExpo(
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
            case IN -> x == 0.0D ? 0.0D : Math.pow(2.0D, 10.0D * x - 10.0D);
            case OUT -> x == 1.0D ? 1.0D : 1.0D - Math.pow(2.0D, -10.0D * x);
            case INOUT -> x == 0.0D || x == 1.0D ? x :
                    x < 0.5D ? Math.pow(2.0D, 20.0D * x - 10.0D) * 0.5D :
                            (2.0D - Math.pow(2.0D, -20.0D * x + 10.0D)) * 0.5D;
        };
    }
}
