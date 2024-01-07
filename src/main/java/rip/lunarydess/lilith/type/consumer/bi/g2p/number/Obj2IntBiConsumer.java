package rip.lunarydess.lilith.type.consumer.bi.g2p.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2IntBiConsumer<V> extends BiConsumer<V, Integer> {
    void acceptInt(final V left, final int right);

    @Deprecated
    default @Override void accept(final V left, final Integer right) {
        this.acceptInt(left, right);
    }

    default @Contract(pure = true) Obj2IntBiConsumer<V> andThenInt(final Obj2IntBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.acceptInt(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Integer> andThen(final BiConsumer<? super V, ? super Integer> after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.accept(l, r);
        };
    }
}
