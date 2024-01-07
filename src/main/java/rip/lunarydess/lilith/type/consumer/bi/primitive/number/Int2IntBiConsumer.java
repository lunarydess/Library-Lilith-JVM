package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Int2IntBiConsumer extends BiConsumer<Integer, Integer> {
    void acceptInt(final int left, final int right);

    @Deprecated
    default @Override void accept(final Integer left, final Integer right) {
        this.acceptInt(left, right);
    }

    default @Contract(pure = true) Int2IntBiConsumer andThenInt(final Int2IntBiConsumer after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.acceptInt(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Integer, Integer> andThen(final BiConsumer<? super Integer, ? super Integer> after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.accept(l, r);
        };
    }
}
