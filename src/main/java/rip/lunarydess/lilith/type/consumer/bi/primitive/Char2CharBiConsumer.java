package rip.lunarydess.lilith.type.consumer.bi.primitive;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Char2CharBiConsumer extends BiConsumer<Character, Character> {
    void acceptChar(final char left, final char right);

    @Deprecated
    default @Override void accept(final Character left, final Character right) {
        this.acceptChar(left, right);
    }

    default @Contract(pure = true) Char2CharBiConsumer andThenChar(final Char2CharBiConsumer after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.acceptChar(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Character, Character> andThen(final BiConsumer<? super Character, ? super Character> after) {
        return (l, r) -> {
            this.acceptChar(l, r);
            after.accept(l, r);
        };
    }
}
