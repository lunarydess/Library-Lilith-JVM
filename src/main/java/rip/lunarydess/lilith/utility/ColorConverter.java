package rip.lunarydess.lilith.utility;

public final class ColorConverter {
    // @formatter:off
    public static int hsvToDec(
            final float hue,
            final float saturation,
            final float value
    ) { return hsvaToDec(hue, saturation, value, 1.0F); }

    public static int hsvaToDec(
            final float hue,
            final float saturation,
            final float value,
            final float alpha
    ) { return rgb_a_ToDec(hsvaToRgba(hue, saturation, value, alpha)); }
    
    public static float[] hsvToRgba(
            final float hue,
            final float saturation,
            final float value
    ) { return hsvaToRgba(hue, saturation, value, 1.0F); }

    public static float[] hsvaToRgba(
            final float hue,
            final float saturation,
            final float value,
            final float alpha
    ) {
        final int hi = Math.round(hue * 6.0F);

        final float f = hue * 6.0F - hi;

        final float q = value * (1.0F - f * saturation);
        final float p = value * (1.0F - saturation);
        final float t = value * (1.0F - (1.0F - f) * saturation);

        return switch(hi) {
            case 0  -> new float[] { value, t, p, alpha };
            case 1  -> new float[] { q, value, p, alpha };
            case 2  -> new float[] { p, value, t, alpha };
            case 3  -> new float[] { p, q, value, alpha };
            case 4  -> new float[] { t, p, value, alpha };
            default -> new float[] { value, p, q, alpha };
        };
    }
    // @formatter:on

    /**
     * @param rgb_a_ values from 0 to 255.
     * @return a decimal with given colors.
     */
    public static int rgb_a_ToDec(final int[] rgb_a_) {
        return rgb_a_ToDec(
                rgb_a_[0],
                rgb_a_[1],
                rgb_a_[2],
                rgb_a_.length > 3 ? rgb_a_[3] : 255
        );
    }

    /**
     * @param rgb_a_ delta values from 0.0 to 1.0.
     * @return a decimal with given values.
     */
    public static int rgb_a_ToDec(final float[] rgb_a_) {
        return rgb_a_ToDec(
                rgb_a_[0],
                rgb_a_[1],
                rgb_a_[2],
                rgb_a_.length > 3 ? rgb_a_[3] : 1.0F
        );
    }

    // @formatter:off
    /**
     * @param color a decimal color value.
     * @return the extracted red, green, blue and alpha values (0 to 255) from the color value.
     */
    public static int[] decToRgba_int(final int color) {
        return new int[]{
                ((color >> 16) & 0xFF),
                ((color >> 8)  & 0xFF),
                ( color        & 0xFF),
                ((color >> 24) & 0xFF)
        };
    }

    /**
     * @param color a decimal color value.
     * @return the extracted red, green, blue and alpha delta-values (0.0 to 1.0) from the color value.
     */
    public static float[] decToRgba_float(final int color) {
        return new float[]{
                ((color >> 16) & 0xFF) / 255.0F,
                ((color >> 8)  & 0xFF) / 255.0F,
                ( color        & 0xFF) / 255.0F,
                ((color >> 24) & 0xFF) / 255.0F
        };
    }

    /**
     * @param red   the value that defines our red in the rgb spectrum (from 0 to 255).
     * @param green the value that defines our green in the rgb spectrum (from 0 to 255).
     * @param blue  the value that defines our blue in the rgb spectrum (from 0 to 255).
     * @return a decimal with given values.
     */
    public static int rgb_a_ToDec(
            final int red,
            final int green,
            final int blue
    ) { return rgb_a_ToDec(red, green, blue, 255); }

    /**
     * @param red   the delta value that defines our red in the rgb spectrum (from 0.0 to 1.0).
     * @param green the delta value that defines our green in the rgb spectrum (from 0.0 to 1.0).
     * @param blue  the delta value that defines our blue in the rgb spectrum (from 0.0 to 1.0).
     * @return a decimal with given values.
     */
    public static int rgb_a_ToDec(
            final float red,
            final float green,
            final float blue
    ) { return rgb_a_ToDec(red, green, blue, 1.0F); }

    /**
     * @param red   the value that defines our red in the rgba spectrum (from 0 to 255).
     * @param green the value that defines our green in the rgba spectrum (from 0 to 255).
     * @param blue  the value that defines our blue in the rgba spectrum (from 0 to 255).
     * @param alpha the value that defines our alpha in the rgba spectrum (from 0 to 255).
     * @return a decimal with given values.
     */
    public static int rgb_a_ToDec(
            final int red,
            final int green,
            final int blue,
            final int alpha
    ) { return ((alpha & 0xFF) << 24)
             | ((red   & 0xFF) << 16)
             | ((green & 0xFF) <<  8)
             |  (blue  & 0xFF       ); }

    /**
     * @param red   the delta value that defines our red in the rgba spectrum (from 0.0 to 1.0).
     * @param green the delta value that defines our green in the rgba spectrum (from 0.0 to 1.0).
     * @param blue  the delta value that defines our blue in the rgba spectrum (from 0.0 to 1.0).
     * @param alpha the delta value that defines our alpha in the rgba spectrum (from 0.0 to 1.0).
     * @return a decimal with given values.
     */
    public static int rgb_a_ToDec(
            final float red,
            final float green,
            final float blue,
            final float alpha
    ) { return (((int) (alpha * 255.0F) & 0xFF) << 24)
             | (((int) (red   * 255.0F) & 0xFF) << 16)
             | (((int) (green * 255.0F) & 0xFF) <<  8)
             | ( (int) (blue  * 255.0F) & 0xFF       ); }
    // @formatter:on
}
