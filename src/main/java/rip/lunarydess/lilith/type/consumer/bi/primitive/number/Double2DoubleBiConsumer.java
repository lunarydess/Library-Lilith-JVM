package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Double2DoubleBiConsumer extends BiConsumer<Double, Double> {
    void acceptDouble(final double left, final double right);

    @Deprecated
    default @Override void accept(final Double left, final Double right) {
        this.acceptDouble(left, right);
    }

    default @Contract(pure = true) Double2DoubleBiConsumer andThenDouble(final Double2DoubleBiConsumer after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.acceptDouble(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Double, Double> andThen(final BiConsumer<? super Double, ? super Double> after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.accept(l, r);
        };
    }
}
