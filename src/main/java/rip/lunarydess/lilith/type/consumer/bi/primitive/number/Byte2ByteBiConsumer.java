package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Byte2ByteBiConsumer extends BiConsumer<Byte, Byte> {
    void acceptByte(final byte left, final byte right);

    @Deprecated
    default @Override void accept(final Byte left, final Byte right) {
        this.acceptByte(left, right);
    }

    default @Contract(pure = true) Byte2ByteBiConsumer andThenByte(final Byte2ByteBiConsumer after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.acceptByte(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Byte, Byte> andThen(final BiConsumer<? super Byte, ? super Byte> after) {
        return (l, r) -> {
            this.acceptByte(l, r);
            after.accept(l, r);
        };
    }
}
