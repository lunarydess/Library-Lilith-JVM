package rip.lunarydess.lilith.type.consumer.bi.p2g;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Byte2ObjBiConsumer<V> extends BiConsumer<Byte, V> {
    void acceptByte(final byte left, final V right);

    @Deprecated
    default @Override void accept(final Byte left, final V right) {
        this.acceptByte(left, right);
    }

    default @Contract(pure = true) Byte2ObjBiConsumer<V> andThenByte(final Byte2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.acceptByte(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Byte, V> andThen(final BiConsumer<? super Byte, ? super V> after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.accept(l, r);
        };
    }
}
