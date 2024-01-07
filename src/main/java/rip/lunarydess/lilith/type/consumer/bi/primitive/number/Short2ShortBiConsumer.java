package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Short2ShortBiConsumer extends BiConsumer<Short, Short> {
    void acceptShort(final short left, final short right);

    @Deprecated
    default @Override void accept(final Short left, final Short right) {
        this.acceptShort(left, right);
    }

    default @Contract(pure = true) Short2ShortBiConsumer andThenShort(final Short2ShortBiConsumer after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.acceptShort(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Short, Short> andThen(final BiConsumer<? super Short, ? super Short> after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.accept(l, r);
        };
    }
}
