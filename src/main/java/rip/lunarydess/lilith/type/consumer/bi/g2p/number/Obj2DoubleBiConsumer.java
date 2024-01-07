package rip.lunarydess.lilith.type.consumer.bi.g2p.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2DoubleBiConsumer<V> extends BiConsumer<V, Double> {
    void acceptDouble(final V left, final double right);

    @Deprecated
    default @Override void accept(final V left, final Double right) {
        this.acceptDouble(left, right);
    }

    default @Contract(pure = true) Obj2DoubleBiConsumer<V> andThenDouble(final Obj2DoubleBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.acceptDouble(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Double> andThen(final BiConsumer<? super V, ? super Double> after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.accept(l, r);
        };
    }
}
