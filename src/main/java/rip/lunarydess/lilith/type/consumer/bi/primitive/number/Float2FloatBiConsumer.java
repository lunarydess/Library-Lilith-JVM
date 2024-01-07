package rip.lunarydess.lilith.type.consumer.bi.primitive.number;

import org.jetbrains.annotations.Contract;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface Float2FloatBiConsumer extends BiConsumer<Float, Float> {
    void acceptFloat(final float left, final float right);

    @Deprecated
    default @Override void accept(final Float left, final Float right) {
        this.acceptFloat(left, right);
    }

    default @Contract(pure = true) Float2FloatBiConsumer andThenFloat(final Float2FloatBiConsumer after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.acceptFloat(l, r);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override BiConsumer<Float, Float> andThen(final BiConsumer<? super Float, ? super Float> after) {
        return (l, r) -> {
            this.acceptFloat(l, r);
            after.accept(l, r);
        };
    }
}
