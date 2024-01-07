package rip.lunarydess.lilith.type.consumer.bi.p2g.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Int2ObjBiConsumer<V> extends BiConsumer<Integer, V> {
    void acceptInt(final int left, final V right);

    @Deprecated
    default @Override void accept(final Integer left, final V right) {
        this.acceptInt(left, right);
    }

    default @Contract(pure = true) Int2ObjBiConsumer<V> andThenInt(final Int2ObjBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.acceptInt(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Integer, V> andThen(final BiConsumer<? super Integer, ? super V> after) {
        return (l, r) -> {
            this.acceptInt(l, r);
            after.accept(l, r);
        };
    }
}
