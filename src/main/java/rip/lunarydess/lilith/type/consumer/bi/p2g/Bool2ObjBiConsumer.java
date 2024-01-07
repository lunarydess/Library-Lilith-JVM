package rip.lunarydess.lilith.type.consumer.bi.p2g;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Bool2ObjBiConsumer<V> extends BiConsumer<Boolean, V> {
    void acceptBool(final boolean left, final V right);

    @Deprecated
    default @Override void accept(final Boolean left, final V right) {
        this.acceptBool(left, right);
    }

    default @Contract(pure = true) Bool2ObjBiConsumer<V> andThenBool(final Bool2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.acceptBool(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Boolean, V> andThen(final BiConsumer<? super Boolean, ? super V> after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.accept(l, r);
        };
    }
}
