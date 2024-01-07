package rip.lunarydess.lilith.type.consumer.bi.p2g.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Long2ObjBiConsumer<V> extends BiConsumer<Long, V> {
    void acceptLong(final long left, final V right);

    @Deprecated
    default @Override void accept(final Long left, final V right) {
        this.acceptLong(left, right);
    }

    default @Contract(pure = true) Long2ObjBiConsumer<V> andThenLong(final Long2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.acceptLong(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Long, V> andThen(final BiConsumer<? super Long, ? super V> after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.accept(l, r);
        };
    }
}
