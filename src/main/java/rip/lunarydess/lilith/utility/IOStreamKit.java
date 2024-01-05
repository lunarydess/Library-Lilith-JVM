package rip.lunarydess.lilith.utility;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public final class IOStreamKit {
    /**
     * @param inputStream the input-stream you want as a string.
     * @return the read string OR null. be careful.
     */
    public static <I extends InputStream>
    @Nullable String inputStreamAsString(final I inputStream) {
        return inputStreamAsString(inputStream, Throwable::printStackTrace);
    }

    /**
     * @param inputStream the input-stream you want as a string.
     * @param onError     will be accepted when an error occurs.
     * @return the read string OR null. be careful.
     */
    public static <I extends InputStream>
    @Nullable String inputStreamAsString(
            final I inputStream,
            final Consumer<Throwable> onError
    ) {
        final int initialCapacity = 8192;
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream), initialCapacity)) {
            final StringBuilder output = new StringBuilder(initialCapacity);
            in.lines().forEach(output::append);
            return output.toString();
        } catch (final IOException ioException) {
            onError.accept(ioException);
            return null;
        }
    }
}
