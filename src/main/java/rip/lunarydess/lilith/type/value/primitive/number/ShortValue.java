package rip.lunarydess.lilith.type.value.primitive.number;

import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.number.Short2ShortBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class ShortValue implements IValue {
    // @formatter:off
    private static final Short2ShortBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on

    private final Short2ShortBiConsumer onChange;
    private final String name;
    private final short min, max, step;
    private short value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value
    ) { this(name, value, (short) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(short)} or {@link #setValueUnchecked(Object)} gets called.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value, final @Nullable Short2ShortBiConsumer onChange
    ) { this(name, value, onChange, (short) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value,
        final short step
    ) { this(name, value, Short.MIN_VALUE, Short.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(short)} or {@link #setValueUnchecked(Object)} gets called.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value, final @Nullable Short2ShortBiConsumer onChange,
        final short step
    ) { this(name, value, onChange, Short.MIN_VALUE, Short.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value,
        final short min, final short max
    ) { this(name, value, null, min, max, (short) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(short)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value, final @Nullable Short2ShortBiConsumer onChange,
        final short min, final short max
    ) { this(name, value, onChange, min, max, (short) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ShortValue(
        final String name,
        final short value,
        final short min, final short max,
        final short step
    ) { this(name, value, null, min, max, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(short)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    public ShortValue(
        final String name,
        final short value, final @Nullable Short2ShortBiConsumer onChange,
        final short min, final short max,
        final short step
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
    public short getMin() {
        return this.min;
    }

    /**
     * @return the highest possible value.
     */
    public short getMax() {
        return this.max;
    }

    /**
     * @return the in-/decrease value.
     */
    public short getStep() {
        return this.step;
    }

    /**
     * @return the current value.
     */
    public short getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(short value) {
        value = value < this.min ? this.min : value > this.max ? this.max : value;
        this.onChange.acceptShort(this.value, value);
        this.value = value;
    }

    /**
     * @apiNote please use {@link #setValue(short)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((short) value);
    }

    public void increase() {
        this.setValue((short) (this.getValue() + this.getStep()));
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     */
    public void increase(final int times) {
        this.setValue((short) (this.getValue() + (this.getStep() * times)));
    }

    /**
     * @return the new value increased by one.
     */
    public short increaseAndGet() {
        this.increase();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the new value.
     */
    public short increaseAndGet(final int times) {
        this.increase(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets increased.
     */
    public short getAndIncrease() {
        final short previousValue = this.getValue();
        this.increase();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the value before it gets increased.
     */
    public short getAndIncrease(final int times) {
        final short previousValue = this.getValue();
        this.increase(times);
        return previousValue;
    }

    public void decrease() {
        this.setValue((short) (this.getValue() - this.getStep()));
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     */
    public void decrease(final int times) {
        this.setValue((short) (this.getValue() - (this.getStep() * times)));
    }

    /**
     * @return the new value decreased by one.
     */
    public short decreaseAndGet() {
        this.decrease();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the new value.
     */
    public short decreaseAndGet(final int times) {
        this.decrease(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets decreased.
     */
    public short getAndDecrease() {
        final short previousValue = this.getValue();
        this.decrease();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the value before it gets decreased.
     */
    public short getAndDecrease(final int times) {
        final short previousValue = this.getValue();
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
            "ShortValue{name=%s, value=%d, min=%d, max=%d, step=%d}",
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
