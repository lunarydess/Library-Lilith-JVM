package rip.lunarydess.lilith.type.consumer.bi.g2p.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2ShortBiConsumer<V> extends BiConsumer<V, Short> {
    void acceptShort(final V left, final short right);

    @Deprecated
    default @Override void accept(final V left, final Short right) {
        this.acceptShort(left, right);
    }

    default @Contract(pure = true) Obj2ShortBiConsumer<V> andThenShort(final Obj2ShortBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.acceptShort(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Short> andThen(final BiConsumer<? super V, ? super Short> after) {
        return (l, r) -> {
            this.acceptShort(l, r);
            after.accept(l, r);
        };
    }
}
