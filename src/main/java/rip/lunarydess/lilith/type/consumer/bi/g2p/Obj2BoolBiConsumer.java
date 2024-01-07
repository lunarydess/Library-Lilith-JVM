package rip.lunarydess.lilith.type.consumer.bi.g2p;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2BoolBiConsumer<V> extends BiConsumer<V, Boolean> {
    void acceptBool(final V left, final boolean right);

    @Deprecated
    default @Override void accept(final V left, final Boolean right) {
        this.acceptBool(left, right);
    }

    default @Contract(pure = true) Obj2BoolBiConsumer<V> andThenBool(final Obj2BoolBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.acceptBool(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Boolean> andThen(final BiConsumer<? super V, ? super Boolean> after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.accept(l, r);
        };
    }
}
