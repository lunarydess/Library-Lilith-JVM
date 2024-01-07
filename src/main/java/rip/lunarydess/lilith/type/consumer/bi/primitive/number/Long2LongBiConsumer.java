package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Long2LongBiConsumer extends BiConsumer<Long, Long> {
    void acceptLong(final long left, final long right);

    @Deprecated
    default @Override void accept(final Long left, final Long right) {
        this.acceptLong(left, right);
    }

    default @Contract(pure = true) Long2LongBiConsumer andThenLong(final Long2LongBiConsumer after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.acceptLong(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Long, Long> andThen(final BiConsumer<? super Long, ? super Long> after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.accept(l, r);
        };
    }
}
