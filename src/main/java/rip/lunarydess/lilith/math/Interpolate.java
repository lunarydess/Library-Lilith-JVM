package rip.lunarydess.lilith.math;

public final class Interpolate {
    public static float lerp(final float delta, final float min, final float max) {
        return Arithmetics.clamp((1.0F - delta) * min + delta * max, min, max);
    }

    public static double lerp(final double delta, final double min, final double max) {
        return Arithmetics.clamp((1.0D - delta) * min + delta * max, min, max);
    }
}
