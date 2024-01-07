package rip.lunarydess.lilith.type.consumer.single.number;

import org.jetbrains.annotations.Contract;

import java.util.function.Consumer;

@FunctionalInterface
public interface FloatConsumer extends Consumer<Float> {
    void acceptFloat(final float value);

    @Deprecated
    default @Override void accept(final Float value) {
        this.acceptFloat(value);
    }

    default @Contract(pure = true) FloatConsumer andThenFloat(final FloatConsumer after) {
        return (value) -> {
            this.acceptFloat(value);
            after.acceptFloat(value);
        };
    }

    @Deprecated
    @Contract(pure = true)
    default @Override Consumer<Float> andThen(final Consumer<? super Float> after) {
        return (value) -> {
            this.acceptFloat(value);
            after.accept(value);
        };
    }
}
