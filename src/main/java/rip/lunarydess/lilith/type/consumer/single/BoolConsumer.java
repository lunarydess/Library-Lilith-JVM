package rip.lunarydess.lilith.type.consumer.single;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface BoolConsumer extends Consumer<Boolean> {
    void acceptBool(final boolean value);

    @Deprecated
    default @Override void accept(final Boolean value) {
        this.acceptBool(value);
    }

    default @Contract(pure = true) BoolConsumer andThenBool(final BoolConsumer after) {
        return (value) -> {
            this.acceptBool(value);
            after.acceptBool(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Boolean> andThen(final Consumer<? super Boolean> after) {
        return (value) -> {
            this.acceptBool(value);
            after.accept(value);
        };
    }
}
