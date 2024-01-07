package rip.lunarydess.lilith.type.consumer.single.number;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface ShortConsumer extends Consumer<Short> {
    void acceptShort(final short value);

    @Deprecated
    default @Override void accept(final Short value) {
        this.acceptShort(value);
    }

    default @Contract(pure = true) ShortConsumer andThenShort(final ShortConsumer after) {
        return (value) -> {
            this.acceptShort(value);
            after.acceptShort(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Short> andThen(final Consumer<? super Short> after) {
        return (value) -> {
            this.acceptShort(value);
            after.accept(value);
        };
    }
}
