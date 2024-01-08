package rip.lunarydess.lilith.math;

public final class Interpolate {
    public static float lerp(final float min, final float max, final float delta) {
        return Arithmetics.clamp((1.0F - delta) * min + delta * max, min, max);
    }

    public static double lerp(final double min, final double max, final double delta) {
        return Arithmetics.clamp((1.0D - delta) * min + delta * max, min, max);
    }
}
