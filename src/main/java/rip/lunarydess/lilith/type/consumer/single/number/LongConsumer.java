package rip.lunarydess.lilith.type.consumer.single.number;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface LongConsumer extends Consumer<Long> {
    void acceptLong(final long value);

    @Deprecated
    default @Override void accept(final Long value) {
        this.acceptLong(value);
    }

    default @Contract(pure = true) LongConsumer andThenLong(final LongConsumer after) {
        return (value) -> {
            this.acceptLong(value);
            after.acceptLong(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Long> andThen(final Consumer<? super Long> after) {
        return (value) -> {
            this.acceptLong(value);
            after.accept(value);
        };
    }
}
