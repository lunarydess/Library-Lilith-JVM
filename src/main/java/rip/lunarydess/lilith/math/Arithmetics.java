package rip.lunarydess.lilith.math;


import org.jetbrains.annotations.Contract;

public final class Arithmetics {
    /**
     * @param values the numbers we want to have the sum of.
     * @return the sum.
     */
    public static short sum(final short... values) {
        short value = 0;
        for (final short i : values) value += i;
        return value;
    }

    /**
     * @param values the numbers we want to have the sum of.
     * @return the sum.
     */
    public static int sum(final int... values) {
        int value = 0;
        for (final int i : values) value += i;
        return value;
    }

    /**
     * @param values the numbers we want to have the sum of.
     * @return the sum.
     */
    public static long sum(final long... values) {
        long value = 0;
        for (final long i : values) value += i;
        return value;
    }

    /**
     * @param values our numbers we are working with.
     * @return the sum of provided numbers.
     */
    public static float sum(final float... values) {
        float value = 0;
        for (final float f : values) value += f;
        return value;
    }

    /**
     * @param values the numbers we want to have the sum of.
     * @return the sum.
     */
    public static double sum(final double... values) {
        double value = 0;
        for (final double d : values) value += d;
        return value;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static short avgShort(final short... numbers) {
        return (short) (sum(numbers) / numbers.length);
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static short avgShort(final int... numbers) {
        return (short) (sum(numbers) / numbers.length);
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static short avgShort(final long... numbers) {
        return (short) (sum(numbers) / numbers.length);
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static short avgShort(final float... numbers) {
        return (short) (sum(numbers) / numbers.length);
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static short avgShort(final double... numbers) {
        return (short) (sum(numbers) / numbers.length);
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static int avgInt(final short... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static int avgInt(final int... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static int avgInt(final long... numbers) {
        return (int) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static int avgInt(final float... numbers) {
        return (int) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static int avgInt(final double... numbers) {
        return (int) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static long avgLong(final short... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static long avgLong(final int... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static long avgLong(final long... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static long avgLong(final float... numbers) {
        return (long) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static long avgLong(final double... numbers) {
        return (int) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static float avgFloat(final short... numbers) {
        return (float) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static float avgFloat(final int... numbers) {
        return (float) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static float avgFloat(final long... numbers) {
        return (float) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static float avgFloat(final float... numbers) {
        return sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static float avgFloat(final double... numbers) {
        return (float) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static double avgDouble(final short... numbers) {
        return (double) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static double avgDouble(final int... numbers) {
        return (double) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static double avgDouble(final long... numbers) {
        return (double) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static double avgDouble(final float... numbers) {
        return (double) sum(numbers) / numbers.length;
    }

    /**
     * @param numbers the numbers we want to have the average value of.
     * @return the average value of provided numbers.
     */
    public static double avgDouble(final double... numbers) {
        return sum(numbers) / numbers.length;
    }

    // @formatter:off
    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static byte clamp(
            final byte value,
            final byte minimum,
            final byte maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }

    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static short clamp(
            final short value,
            final short minimum,
            final short maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }

    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static int clamp(
            final int value,
            final int minimum,
            final int maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }

    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static long clamp(
            final long value,
            final long minimum,
            final long maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }

    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static float clamp(
            final float value,
            final float minimum,
            final float maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }

    /**
     * @param value   our value we want to clamp.
     * @param minimum the least value that is allowed.
     * @param maximum the highest value that is allowed.
     * @return a value that does not exceed minimum or maximum.
     */
    public static double clamp(
            final double value,
            final double minimum,
            final double maximum
    ) { return value < minimum ? minimum : value > maximum ? maximum : value; }
    // @formatter:on
}
