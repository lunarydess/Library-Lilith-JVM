
 package rip.lunarydess.lilith.type.consumer.bi.p2g;

 import org.jetbrains.annotations.Contract;

 import java.util.function.BiConsumer;

@FunctionalInterface
public interface Char2ObjBiConsumer<V> extends BiConsumer<Character, V> {
    void acceptChar(final char left, final V right);

    @Deprecated
    default @Override void accept(final Character left, final V right) {
        this.acceptChar(left, right);
    }

    default @Contract(pure = true) Char2ObjBiConsumer<V> andThenChar(final Char2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.acceptChar(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Character, V> andThen(final BiConsumer<? super Character, ? super V> after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.accept(l, r);
        };
    }
}
