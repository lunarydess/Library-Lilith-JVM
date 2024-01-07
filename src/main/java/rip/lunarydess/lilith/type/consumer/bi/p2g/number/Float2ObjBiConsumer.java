package rip.lunarydess.lilith.type.consumer.bi.p2g.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Float2ObjBiConsumer<V> extends BiConsumer<Float, V> {
    void acceptFloat(final float left, final V right);

    @Deprecated
    default @Override void accept(final Float left, final V right) {
        this.acceptFloat(left, right);
    }

    default @Contract(pure = true) Float2ObjBiConsumer<V> andThenFloat(final Float2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.acceptFloat(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Float, V> andThen(final BiConsumer<? super Float, ? super V> after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.accept(l, r);
        };
    }
}
