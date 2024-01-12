package rip.lunarydess.lilith.type.value.primitive.number;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.number.Float2FloatBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class FloatValue implements IValue {
    // @formatter:off
    private static final Float2FloatBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on

    private final Float2FloatBiConsumer onChange;
    private final String name;
    private final float min, max, step;
    private float value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value
    ) { this(name, value, (float) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(float)} or {@link #setValueUnchecked(Object)} gets called.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value, final @Nullable Float2FloatBiConsumer onChange
    ) { this(name, value, onChange, (float) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value,
        final float step
    ) { this(name, value, Float.MIN_VALUE, Float.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(float)} or {@link #setValueUnchecked(Object)} gets called.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value, final @Nullable Float2FloatBiConsumer onChange,
        final float step
    ) { this(name, value, onChange, Float.MIN_VALUE, Float.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value,
        final float min, final float max
    ) { this(name, value, null, min, max, (float) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(float)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value, final @Nullable Float2FloatBiConsumer onChange,
        final float min, final float max
    ) { this(name, value, onChange, min, max, (float) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public FloatValue(
        final String name,
        final float value,
        final float min, final float max,
        final float step
    ) { this(name, value, null, min, max, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(float)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    public FloatValue(
        final String name,
        final float value, final @Nullable Float2FloatBiConsumer onChange,
        final float min, final float max,
        final float step
    ) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
        this.onChange = onChange == null ? EMPTY_CONSUMER : onChange;
        this.setValue(value);
    }

    /**
     * @return the lowest possible value.
     */
    public float getMin() {
        return this.min;
    }

    /**
     * @return the highest possible value.
     */
    public float getMax() {
        return this.max;
    }

    /**
     * @return the in-/decrease value.
     */
    public float getStep() {
        return this.step;
    }

    /**
     * @return the current value.
     */
    public float getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(float value) {
        value = value < this.min ? this.min : value > this.max ? this.max : value;
        this.onChange.acceptFloat(this.value, value);
        this.value = value;
    }

    /**
     * @apiNote please use {@link #setValue(float)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((float) value);
    }

    public void increase() {
        this.setValue(this.getValue() + this.getStep());
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     */
    public void increase(final int times) {
        this.setValue(this.getValue() + (this.getStep() * times));
    }

    /**
     * @return the new value increased by one.
     */
    public float increaseAndGet() {
        this.increase();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the new value.
     */
    public float increaseAndGet(final int times) {
        this.increase(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets increased.
     */
    public float getAndIncrease() {
        final float previousValue = this.getValue();
        this.increase();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the value before it gets increased.
     */
    public float getAndIncrease(final int times) {
        final float previousValue = this.getValue();
        this.increase(times);
        return previousValue;
    }

    public void decrease() {
        this.setValue(this.getValue() - this.getStep());
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     */
    public void decrease(final int times) {
        this.setValue(this.getValue() - (this.getStep() * times));
    }

    /**
     * @return the new value decreased by one.
     */
    public float decreaseAndGet() {
        this.decrease();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the new value.
     */
    public float decreaseAndGet(final int times) {
        this.decrease(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets decreased.
     */
    public float getAndDecrease() {
        final float previousValue = this.getValue();
        this.decrease();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the value before it gets decreased.
     */
    public float getAndDecrease(final int times) {
        final float previousValue = this.getValue();
        this.decrease(times);
        return previousValue;
    }

    /**
     * @return the name of the current value.
     */
    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format(
            "FloatValue{name=%s, value=%f, min=%f, max=%f, step=%f}",
            this.getName(),
            this.getValue(),
            this.getMin(),
            this.getMax(),
            this.getStep()
        );
    }

    public @Override int hashCode() {
        return Objects.hash(
            this.getName(),
            this.getValue(),
            this.getMin(),
            this.getMax(),
            this.getStep()
        );
    }
}
