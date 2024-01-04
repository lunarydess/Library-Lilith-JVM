package rip.lunarydess.lilith.utility;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.List;

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
    public static String formatBytes(double size) {
        int unitIndex = (int) (Math.log(size) / Math.log(1024));
        unitIndex = unitIndex < 0 ? 0 : Math.min(unitIndex, BYTE_UNITS.length - 1);
        size /= Math.pow(1024, unitIndex);
        return String.format("%s %s", BYTE_FORMAT.format(size), BYTE_UNITS[unitIndex]);
    }

    public static String password(
            final int length,
            final boolean lower,
            final boolean upper,
            final boolean number,
            final boolean special
    ) {
        String[] categories = new String[0];

        if (lower) categories = ArrayKit.add(String[]::new, categories, CHARS_LOWER);
        if (upper) categories = ArrayKit.add(String[]::new, categories, CHARS_UPPER);
        if (number) categories = ArrayKit.add(String[]::new, categories, CHARS_NUMBER);
        if (special) categories = ArrayKit.add(String[]::new, categories, CHARS_SPECIAL);

        final StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            final String charCategory = categories[RANDOM.nextInt(0, categories.length - 1)];
            password.append(RANDOM.nextInt(charCategory.length()));
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
