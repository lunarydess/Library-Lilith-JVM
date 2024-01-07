
 package rip.lunarydess.lilith.type.consumer.bi.g2p.number;

 import org.jetbrains.annotations.Contract;

 import java.util.function.BiConsumer;

@FunctionalInterface
public interface Obj2LongBiConsumer<V> extends BiConsumer<V, Long> {
    void acceptLong(final V left, final long right);

    @Deprecated
    default @Override void accept(final V left, final Long right) {
        this.acceptLong(left, right);
    }

    default @Contract(pure = true) Obj2LongBiConsumer<V> andThenLong(final Obj2LongBiConsumer<V> after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.acceptLong(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<V, Long> andThen(final BiConsumer<? super V, ? super Long> after) {
        return (l, r) -> {
            this.acceptLong(l, r);
            after.accept(l, r);
        };
    }
}
