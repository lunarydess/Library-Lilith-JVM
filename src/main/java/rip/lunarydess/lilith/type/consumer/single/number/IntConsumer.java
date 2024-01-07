package rip.lunarydess.lilith.type.consumer.single.number;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface IntConsumer extends Consumer<Integer> {
    void acceptInt(final int value);

    @Deprecated
    default @Override void accept(final Integer value) {
        this.acceptInt(value);
    }

    default @Contract(pure = true) IntConsumer andThenInt(final IntConsumer after) {
        return (value) -> {
            this.acceptInt(value);
            after.acceptInt(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Integer> andThen(final Consumer<? super Integer> after) {
        return (value) -> {
            this.acceptInt(value);
            after.accept(value);
        };
    }
}
