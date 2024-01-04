package rip.lunarydess.lilith.utility;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public final class IOStreamKit {
    /**
     * @param <I>         the resolved input-stream type
     * @param inputStream the input-stream you want as a string
     * @return the read string OR null, so be careful!
     */
    public static <I extends InputStream>
    @Nullable String inputStreamAsString(final I inputStream) {
        return inputStreamAsString(inputStream, Throwable::printStackTrace);
    }

    /**
     * @param <I>         the resolved input-stream type
     * @param inputStream the input-stream you want as a string
     * @param onError     will be accepted when an error occurs
     * @return the read string OR null, so be careful!
     */
    public static <I extends InputStream>
    @Nullable String inputStreamAsString(
            final I inputStream,
            final Consumer<Throwable> onError
    ) {
        final StringBuilder output = new StringBuilder();
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) output.append(inputLine);
            return output.toString();
        } catch (final Throwable throwable) {
            onError.accept(throwable);
            return null;
        }
    }
}
