package rip.lunarydess.lilith.type.consumer.bi.g2p.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2FloatBiConsumer<V> extends BiConsumer<V, Float> {
    void acceptFloat(final V left, final float right);

    @Deprecated
    default @Override void accept(final V left, final Float right) {
        this.acceptFloat(left, right);
    }

    default @Contract(pure = true) Obj2FloatBiConsumer<V> andThenFloat(final Obj2FloatBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.acceptFloat(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Float> andThen(final BiConsumer<? super V, ? super Float> after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.accept(l, r);
        };
    }
}
