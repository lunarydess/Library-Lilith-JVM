import rip.lunarydess.utility.ArrayKit;

import java.security.SecureRandom;

public class Test {
    private static final String TEST_1 = "1337", TEST_2 = "7331";

    private static final String[] TEST = new String[1000];

    static {
        final SecureRandom random = new SecureRandom();
        final int
                save1 = random.nextInt(TEST.length),
                save2 = random.nextInt(TEST.length);
        for (int i = 0; i < TEST.length; i++) {
            if (i == save1 || i == save2) continue;
            TEST[i] = String.valueOf(random.nextInt(TEST.length));
        }
        TEST[save1] = TEST_1;
        TEST[save2] = TEST_2;
    }

    public static void main(final String... args) {
        System.out.println(ArrayKit.toString(TEST));
        
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i : ArrayKit.indicesOf(TEST, TEST_1, TEST_2)) {
            stringBuilder.append(i).append(' ');
        }
        System.out.println(stringBuilder);
    }
}
