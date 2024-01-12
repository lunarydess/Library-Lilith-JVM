package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingCirc extends AbstractEasing {
    // @formatter:off
    public EasingCirc(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingCirc(
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
            case IN -> 1.0D - Math.sqrt(1.0D - (x * x));
            case OUT -> Math.sqrt(1.0D - ((x - 1.0D) * (x - 1.0D)));
            case INOUT -> x < 0.5 ? (1.0D - Math.sqrt(1.0D - ((2.0D * x) * (2.0D * x)))) * 0.5D :
                    (Math.sqrt(1.0D - ((-2.0D * x + 2.0D) * (-2.0D * x + 2.0D))) + 1.0D) * 0.5D;
        };
    }
}
