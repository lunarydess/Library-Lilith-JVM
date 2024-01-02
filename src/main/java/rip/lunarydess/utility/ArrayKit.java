package rip.lunarydess.utility;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class ArrayKit {
    public static <T> T[] add(final IntFunction<T[]> factory, final T[] array, final T adding) {
        return add(factory, array, adding, array.length - 1);
    }

    public static <T> T[] add(final IntFunction<T[]> factory, final T[] array, final T adding, final int index) {
        final var result = factory.apply(array.length + 1);
        if (result.length == 0 || index < -1 || index > array.length - 1) return factory.apply(0);

        System.arraycopy(array, 0, result, 0, index + 1);

        final int addIndex = Math.max(index, 0);
        System.arraycopy(array, addIndex, result, addIndex + 1, array.length - addIndex);

        result[index + 1] = adding;
        return result;
    }


    public static <T> T[] remove(final IntFunction<T[]> factory, final T[] array, final T removing) {
        return remove(factory, array, indexOf(array, removing));
    }

    /**
     * @param array   the array we are working with.
     * @param index   the position where the object needs to be removed.
     * @param factory for example '{@link String}[]::new'.
     * @return a new copied array that does not have the given value from the defined index.
     * @apiNote example: ArrayKit.remove(array, index, String[]::new);
     */
    public static <T> T[] remove(final IntFunction<T[]> factory, final T[] array, final int index) {
        var result = factory.apply(array.length - 1);
        if (result.length == 0 || index < 0 || index > array.length - 1) return factory.apply(0);

        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - (index + 1));

        return result;
    }

    public static <T> int indexOf(final T[] array, final T searching) {
        for (int i = 0; i < array.length; i++)
            if (array[i].equals(searching)) return i;
        return -1;
    }

    @SafeVarargs
    public static <T> int[] indicesOf(final T[] array, final T... searching) {
        final int[] indices = new int[searching.length];
        for (int i = 0; i < searching.length; i++)
            indices[i] = indexOf(array, searching[i]);
        return indices;
    }

    /**
     * @param <T>    the object we let look up.
     * @param values the objects we want to output as a readable string.
     * @return a readable single-line-string from our contents.
     * @apiNote example: ArrayKit.toString(String::toString, new String[]{"test123", "test213", "test321"});
     */
    @SafeVarargs
    public static <T> String toString(final T... values) {
        return toString(T::toString, values);
    }

    /**
     * @param <T>        the object we let look up.
     * @param stringFunc the function that fetches a string from the (looked up) type-parameter.
     * @param values     the objects we want to output as a readable string.
     * @return a readable single-line-string from our contents.
     * @apiNote example: ArrayKit.toString(String::toString, new String[]{"test123", "test213", "test321"});
     */
    @SafeVarargs
    public static <T> String toString(
            final Function<T, String> stringFunc,
            final T... values
    ) {
        if (values.length == 0) return "[]";

        final StringBuilder returnVal = new StringBuilder(String.format("[%s", values[0]));
        if (values.length != 1) for (int i = 1; i < values.length; i++) {
            final T appending = values[i];
            returnVal.append(String.format(", %s", appending == null ? "null" : stringFunc.apply(appending)));
        }

        return returnVal.append("]").toString();
    }

    /**
     * @param <T>     the object we let lookup.
     * @param <L>     the list-type we let lookup.
     * @param array   the given array.
     * @param factory for example '{@link String}[]::new'.
     * @return the list with all contents from the provided array.
     * @apiNote example: ArrayKit.asList(array, {@link java.util.concurrent.CopyOnWriteArrayList}::new);
     */
    public static <T, L extends @NotNull List<@NotNull T>> @NotNull L asList(
            final @NotNull T @NotNull [] array,
            final @NotNull Supplier<@NotNull L> factory
    ) {
        final L list = factory.get();
        list.addAll(List.of(array));
        return list;
    }
}
