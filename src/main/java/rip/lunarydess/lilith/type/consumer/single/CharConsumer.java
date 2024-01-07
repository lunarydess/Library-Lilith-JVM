package rip.lunarydess.lilith.type.consumer.single;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface CharConsumer extends Consumer<Character> {
    void acceptChar(final char value);

    @Deprecated
    default @Override void accept(final Character value) {
        this.acceptChar(value);
    }

    default @Contract(pure = true) CharConsumer andThenChar(final CharConsumer after) {
        return (value) -> {
            this.acceptChar(value);
            after.acceptChar(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Character> andThen(final Consumer<? super Character> after) {
        return (value) -> {
            this.acceptChar(value);
            after.accept(value);
        };
    }
}
