package rip.lunarydess.lilith.type.consumer.single.number;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface DoubleConsumer extends Consumer<Double> {
    void acceptDouble(final double value);

    @Deprecated
    default @Override void accept(final Double value) {
        this.acceptDouble(value);
    }

    default @Contract(pure = true) DoubleConsumer andThenDouble(final DoubleConsumer after) {
        return (value) -> {
            this.acceptDouble(value);
            after.acceptDouble(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Double> andThen(final Consumer<? super Double> after) {
        return (value) -> {
            this.acceptDouble(value);
            after.accept(value);
        };
    }
}
