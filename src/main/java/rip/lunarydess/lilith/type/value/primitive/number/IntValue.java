package rip.lunarydess.lilith.type.value.primitive.number;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.number.Int2IntBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class IntValue implements IValue {
    // @formatter:off
    private static final Int2IntBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on

    private final Int2IntBiConsumer onChange;
    private final String name;
    private final int min, max, step;
    private int value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value
    ) { this(name, value, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(int)} or {@link #setValueUnchecked(Object)} gets called.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value, final @Nullable Int2IntBiConsumer onChange
    ) { this(name, value, onChange, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value,
        final int step
    ) { this(name, value, Integer.MIN_VALUE, Integer.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(int)} or {@link #setValueUnchecked(Object)} gets called.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value, final @Nullable Int2IntBiConsumer onChange,
        final int step
    ) { this(name, value, onChange, Integer.MIN_VALUE, Integer.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value,
        final int min, final int max
    ) { this(name, value, null, min, max, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(int)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value, final @Nullable Int2IntBiConsumer onChange,
        final int min, final int max
    ) { this(name, value, onChange, min, max, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public IntValue(
        final String name,
        final int value,
        final int min, final int max,
        final int step
    ) { this(name, value, null, min, max, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(int)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    public IntValue(
        final String name,
        final int value, final @Nullable Int2IntBiConsumer onChange,
        final int min, final int max,
        final int step
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
    public int getMin() {
        return this.min;
    }

    /**
     * @return the highest possible value.
     */
    public int getMax() {
        return this.max;
    }

    /**
     * @return the in-/decrease value.
     */
    public int getStep() {
        return this.step;
    }

    /**
     * @return the current value.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(int value) {
        value = value < this.min ? this.min : value > this.max ? this.max : value;
        this.onChange.acceptInt(this.value, value);
        this.value = value;
    }

    /**
     * @apiNote please use {@link #setValue(int)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((int) value);
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
    public int increaseAndGet() {
        this.increase();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the new value.
     */
    public int increaseAndGet(final int times) {
        this.increase(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets increased.
     */
    public int getAndIncrease() {
        final int previousValue = this.getValue();
        this.increase();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the value before it gets increased.
     */
    public int getAndIncrease(final int times) {
        final int previousValue = this.getValue();
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
    public int decreaseAndGet() {
        this.decrease();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the new value.
     */
    public int decreaseAndGet(final int times) {
        this.decrease(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets decreased.
     */
    public int getAndDecrease() {
        final int previousValue = this.getValue();
        this.decrease();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the value before it gets decreased.
     */
    public int getAndDecrease(final int times) {
        final int previousValue = this.getValue();
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
            "IntValue{name=%s, value=%d, min=%d, max=%d, step=%d}",
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
