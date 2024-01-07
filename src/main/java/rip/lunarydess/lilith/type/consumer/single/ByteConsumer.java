package rip.lunarydess.lilith.type.consumer.single;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface ByteConsumer extends Consumer<Byte> {
    void acceptByte(final byte value);

    @Deprecated
    default @Override void accept(final Byte value) {
        this.acceptByte(value);
    }

    default @Contract(pure = true) ByteConsumer andThenByte(final ByteConsumer after) {
        return (value) -> {
            this.acceptByte(value);
            after.acceptByte(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Byte> andThen(final Consumer<? super Byte> after) {
        return (value) -> {
            this.acceptByte(value);
            after.accept(value);
        };
    }
}
