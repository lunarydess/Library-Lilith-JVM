package rip.lunarydess.lilith.math.easing.impl;

import rip.lunarydess.lilith.math.easing.AbstractEasing;

import java.util.concurrent.TimeUnit;

/**
 * @implNote <a href="https://easings.net/">easings.net</a>
 */
public final class EasingElastic extends AbstractEasing {
    // @formatter:off
    public EasingElastic(
            final TimeUnit timeUnit,
            final long time
    ) { this(1.0D, timeUnit, time); }

    public EasingElastic(
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
        final double a = 2.0943951023931953D;
        return switch (type) {
            case IN -> x == 0.0D || x == 1.0D ? x :
                    -Math.pow(2.0D, 10.0D * x - 10.0D) * Math.sin((x * 10.0D - 10.75D) * a);
            case OUT -> x == 0.0D || x == 1.0D ? x :
                    Math.pow(2.0D, -10.0D * x) * Math.sin((x * 10.0D - 0.75D) * a) + 1.0D;
            case INOUT -> x == 0.0D || x == 1.0D ? x :
                    x < 0.5D ?
                            -(Math.pow(2.0D, 20.0D * x - 10.0D)
                                    * Math.sin((20.0D * x - 11.125D) * 1.3962634015954636D))
                                    * 0.5D :
                            Math.pow(2.0D, -20.0D * x + 10.0D)
                                    * Math.sin((20.0D * x - 11.125D) * 1.3962634015954636D)
                                    * 0.5D
                                    + 1.0D;
        };
    }
}
