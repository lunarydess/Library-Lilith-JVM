package rip.lunarydess.lilith.type.consumer.bi.p2g.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Double2ObjBiConsumer<V> extends BiConsumer<Double, V> {
    void acceptDouble(final double left, final V right);

    @Deprecated
    default @Override void accept(final Double left, final V right) {
        this.acceptDouble(left, right);
    }

    default @Contract(pure = true) Double2ObjBiConsumer<V> andThenDouble(final Double2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.acceptDouble(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Double, V> andThen(final BiConsumer<? super Double, ? super V> after) {
        return (l, r) -> {
            this.acceptDouble(l, r);
            after.accept(l, r);
        };
    }
}
