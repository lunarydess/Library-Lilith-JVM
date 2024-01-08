package rip.lunarydess.lilith.math;

public final class Statistics {
    public static float median(final float... data) {
        final int dataLengthHalf = data.length / 2;
        final float valueAtHalf = data[dataLengthHalf];
        return data.length % 2 != 0 ? valueAtHalf : (valueAtHalf + data[dataLengthHalf - 1]) * 0.5F;
    }

    public static double median(final double... data) {
        final int dataLengthHalf = data.length / 2;
        final double valueAtHalf = data[dataLengthHalf];
        return data.length % 2 != 0 ? valueAtHalf : (valueAtHalf + data[dataLengthHalf - 1]) * 0.5F;
    }

    public static float variance(final float... data) {
        final int count = data.length;
        final float sum = Arithmetics.sum(data);
        final float average = sum / count;
        float variance = 0.0F;
        for (final float number : data) {
            final float base = number - average;
            variance += (base * base);
        }
        return variance / count;
    }

    public static double variance(final double... data) {
        final int count = data.length;
        final double average = Arithmetics.sum(data) / count;
        double variance = 0.0F;
        for (final double number : data) {
            final double base = number - average;
            variance += (base * base);
        }
        return variance / count;
    }

    public static float standardDeviation(final float... data) {
        return (float) Math.sqrt(variance(data));
    }

    public static double standardDeviation(final double... data) {
        return Math.sqrt(variance(data));
    }
}
