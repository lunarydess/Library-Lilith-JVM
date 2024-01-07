package rip.lunarydess.lilith.type.consumer.bi.g2p;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2CharBiConsumer<V> extends BiConsumer<V, Character> {
    void acceptChar(final V left, final char right);

    @Deprecated
    default @Override void accept(final V left, final Character right) {
        this.acceptChar(left, right);
    }

    default @Contract(pure = true) Obj2CharBiConsumer<V> andThenChar(final Obj2CharBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.acceptChar(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Character> andThen(final BiConsumer<? super V, ? super Character> after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.accept(l, r);
        };
    }
}
