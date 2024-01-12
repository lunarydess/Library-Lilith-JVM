package rip.lunarydess.lilith.utility;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class StringKit {
    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String CHARS_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CHARS_LOWER = "abdcefghijklmnopqrstuvwxyz";
    private static final String CHARS_SPECIAL = "!@#$%&*()_+-=[]|,./\\?><";
    private static final String CHARS_NUMBER = "0123456789";

    /**
     * @implSpec ISO/IEC 80000-13 standard
     */
    private static final String[] BYTE_UNITS = {"B", "kiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"};
    private static final DecimalFormat BYTE_FORMAT = new DecimalFormat("#,##0.##");

    /**
     * @param strings the strings you want to look up
     * @return the longest string of the list
     */
    public static <L extends List<String>> String longestOf(final L strings) {
        return longestOf(strings.toArray(new String[0]));
    }

    /**
     * @param strings the strings you want to look up
     * @return the longest string of the variadic
     */
    public static String longestOf(final String... strings) {
        String longestString = "";
        long max = Long.MIN_VALUE;
        for (final String string : strings) if (string.length() > max) max = (longestString = string).length();
        return longestString;
    }

    /**
     * @param strings the strings you want to look up
     * @return the longest string of the list
     */
    public static <L extends List<String>> String smallestOf(final L strings) {
        return smallestOf(strings.toArray(new String[0]));
    }

    /**
     * @param strings the strings you want to look up
     * @return the smallest string of the variadic
     */
    public static String smallestOf(final String... strings) {
        String smallestString = "";
        long min = Long.MAX_VALUE;
        for (final String string : strings) if (string.length() < min) min = (smallestString = string).length();
        return smallestString;
    }

    /**
     * @param size the raw bytes
     * @return the bytes as a readable string in ISO/IEC 80000-13 standard
     */
    public static String formatBytes(final double size) {
        final int unitIndex = Math.max(0, Math.min((int) (Math.log(size) / Math.log(1024)), BYTE_UNITS.length - 1));
        return String.format("%s %s", BYTE_FORMAT.format(size / Math.pow(1024, unitIndex)), BYTE_UNITS[unitIndex]);
    }

    /**
     * @param length  the length of our password.
     * @param lower   if lower chars should be appended.
     * @param upper   if upper chars should be appended.
     * @param number  if number chars should be appended.
     * @param special if special chars should be appended.
     */
    public static String password(
            final int length,
            final boolean lower,
            final boolean upper,
            final boolean number,
            final boolean special
    ) {
        final String[] categories;
        String[] tempCategories = new String[0];
        if (lower) tempCategories = ArrayKit.add(String[]::new, tempCategories, CHARS_LOWER);
        if (upper) tempCategories = ArrayKit.add(String[]::new, tempCategories, CHARS_UPPER);
        if (number) tempCategories = ArrayKit.add(String[]::new, tempCategories, CHARS_NUMBER);
        if (special) tempCategories = ArrayKit.add(String[]::new, tempCategories, CHARS_SPECIAL);
        categories = tempCategories;

        final StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            final String charCategory = categories[threadLocalRandom.nextInt(0, categories.length)];
            password.append(charCategory.charAt(threadLocalRandom.nextInt(charCategory.length())));
        }

        return password.toString();
    }

    public static String getLowerChars() {
        return CHARS_LOWER;
    }

    public static String getUpperChars() {
        return CHARS_UPPER;
    }

    public static String getNumberChars() {
        return CHARS_NUMBER;
    }

    public static String getSpecialChars() {
        return CHARS_SPECIAL;
    }
}
