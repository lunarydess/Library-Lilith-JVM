package rip.lunarydess.lilith.type.consumer.bi.p2g.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Short2ObjBiConsumer<V> extends BiConsumer<Short, V> {
    void acceptShort(final short left, final V right);

    @Deprecated
    default @Override void accept(final Short left, final V right) {
        this.acceptShort(left, right);
    }

    default @Contract(pure = true) Short2ObjBiConsumer<V> andThenShort(final Short2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.acceptShort(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Short, V> andThen(final BiConsumer<? super Short, ? super V> after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.accept(l, r);
        };
    }
}
