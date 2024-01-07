package rip.lunarydess.lilith.type.consumer.bi.primitive;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Bool2BoolBiConsumer extends BiConsumer<Boolean, Boolean> {
    void acceptBool(final boolean left, final boolean right);

    @Deprecated
    default @Override void accept(final Boolean left, final Boolean right) {
        this.acceptBool(left, right);
    }

    default @Contract(pure = true) Bool2BoolBiConsumer andThenBool(final Bool2BoolBiConsumer after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.acceptBool(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Boolean, Boolean> andThen(final BiConsumer<? super Boolean, ? super Boolean> after) {
        return (l, r) -> {
            this.acceptBool(l, r);
            after.accept(l, r);
        };
    }
}
