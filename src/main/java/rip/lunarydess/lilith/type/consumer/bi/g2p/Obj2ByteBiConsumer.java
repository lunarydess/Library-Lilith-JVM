package rip.lunarydess.lilith.type.consumer.bi.g2p;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2ByteBiConsumer<V> extends BiConsumer<V, Byte> {
    void acceptByte(final V left, final byte right);

    @Deprecated
    default @Override void accept(final V left, final Byte right) {
        this.acceptByte(left, right);
    }

    default @Contract(pure = true) Obj2ByteBiConsumer<V> andThenByte(final Obj2ByteBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.acceptByte(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Byte> andThen(final BiConsumer<? super V, ? super Byte> after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.accept(l, r);
        };
    }
}
