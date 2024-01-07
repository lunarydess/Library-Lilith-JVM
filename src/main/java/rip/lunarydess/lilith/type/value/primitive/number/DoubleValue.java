package rip.lunarydess.lilith.type.value.primitive.number;

import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.number.Double2DoubleBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class DoubleValue implements IValue {
    // @formatter:off
    private static final Double2DoubleBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on

    private final Double2DoubleBiConsumer onChange;
    private final String name;
    private final double min, max, step;
    private double value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value
    ) { this(name, value, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(double)} or {@link #setValueUnchecked(Object)} gets called.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value, final @Nullable Double2DoubleBiConsumer onChange
    ) { this(name, value, onChange, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value,
        final double step
    ) { this(name, value, Double.MIN_VALUE, Double.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(double)} or {@link #setValueUnchecked(Object)} gets called.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value, final @Nullable Double2DoubleBiConsumer onChange,
        final double step
    ) { this(name, value, onChange, Double.MIN_VALUE, Double.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value,
        final double min, final double max
    ) { this(name, value, null, min, max, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(double)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value, final @Nullable Double2DoubleBiConsumer onChange,
        final double min, final double max
    ) { this(name, value, onChange, min, max, 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public DoubleValue(
        final String name,
        final double value,
        final double min, final double max,
        final double step
    ) { this(name, value, null, min, max, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(double)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    public DoubleValue(
        final String name,
        final double value, final @Nullable Double2DoubleBiConsumer onChange,
        final double min, final double max,
        final double step
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
    public double getMin() {
        return this.min;
    }

    /**
     * @return the highest possible value.
     */
    public double getMax() {
        return this.max;
    }

    /**
     * @return the in-/decrease value.
     */
    public double getStep() {
        return this.step;
    }

    /**
     * @return the current value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(double value) {
        value = value < this.min ? this.min : value > this.max ? this.max : value;
        this.onChange.acceptDouble(this.value, value);
        this.value = value;
    }

    /**
     * @apiNote please use {@link #setValue(double)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((double) value);
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
    public double increaseAndGet() {
        this.increase();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the new value.
     */
    public double increaseAndGet(final int times) {
        this.increase(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets increased.
     */
    public double getAndIncrease() {
        final double previousValue = this.getValue();
        this.increase();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the value before it gets increased.
     */
    public double getAndIncrease(final int times) {
        final double previousValue = this.getValue();
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
    public double decreaseAndGet() {
        this.decrease();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the new value.
     */
    public double decreaseAndGet(final int times) {
        this.decrease(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets decreased.
     */
    public double getAndDecrease() {
        final double previousValue = this.getValue();
        this.decrease();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the value before it gets decreased.
     */
    public double getAndDecrease(final int times) {
        final double previousValue = this.getValue();
        this.decrease(times);
        return previousValue;
    }

    /**
     * @return the name of the current value.
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format(
            "DoubleValue{name=%s, value=%f, min=%f, max=%f, step=%f}",
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
