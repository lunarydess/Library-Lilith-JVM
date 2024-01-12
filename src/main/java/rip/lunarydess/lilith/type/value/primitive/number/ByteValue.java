package rip.lunarydess.lilith.type.value.primitive.number;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.number.Byte2ByteBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class ByteValue implements IValue {
    // @formatter:off
    private static final Byte2ByteBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on

    private final Byte2ByteBiConsumer onChange;
    private final String name;
    private final byte min, max, step;
    private byte value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value
    ) { this(name, value, (byte) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(byte)} or {@link #setValueUnchecked(Object)} gets called.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value, final @Nullable Byte2ByteBiConsumer onChange
    ) { this(name, value, onChange, (byte) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value,
        final byte step
    ) { this(name, value, Byte.MIN_VALUE, Byte.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(byte)} or {@link #setValueUnchecked(Object)} gets called.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value, final @Nullable Byte2ByteBiConsumer onChange,
        final byte step
    ) { this(name, value, onChange, Byte.MIN_VALUE, Byte.MAX_VALUE, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value,
        final byte min, final byte max
    ) { this(name, value, null, min, max, (byte) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(byte)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value, final @Nullable Byte2ByteBiConsumer onChange,
        final byte min, final byte max
    ) { this(name, value, onChange, min, max, (byte) 1); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    // @formatter:off
    public ByteValue(
        final String name,
        final byte value,
        final byte min, final byte max,
        final byte step
    ) { this(name, value, null, min, max, step); } // @formatter:on

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(byte)} or {@link #setValueUnchecked(Object)} gets called.
     * @param min is the lowest value that is possible.
     * @param max is the highest value that is possible.
     * @param step is how much the current value is allowed to in-/decrease.
     */
    public ByteValue(
        final String name,
        final byte value, final @Nullable Byte2ByteBiConsumer onChange,
        final byte min, final byte max,
        final byte step
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
    public byte getMin() {
        return this.min;
    }

    /**
     * @return the highest possible value.
     */
    public byte getMax() {
        return this.max;
    }

    /**
     * @return the in-/decrease value.
     */
    public byte getStep() {
        return this.step;
    }

    /**
     * @return the current value.
     */
    public byte getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(byte value) {
        value = value < this.min ? this.min : value > this.max ? this.max : value;
        this.onChange.acceptByte(this.value, value);
        this.value = value;
    }

    /**
     * @apiNote please use {@link #setValue(byte)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((byte) value);
    }

    public void increase() {
        this.setValue((byte) (this.getValue() + this.getStep()));
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     */
    public void increase(final int times) {
        this.setValue((byte) (this.getValue() + (this.getStep() * times)));
    }

    /**
     * @return the new value increased by one.
     */
    public byte increaseAndGet() {
        this.increase();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the new value.
     */
    public byte increaseAndGet(final int times) {
        this.increase(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets increased.
     */
    public byte getAndIncrease() {
        final byte previousValue = this.getValue();
        this.increase();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} up.
     * @return the value before it gets increased.
     */
    public byte getAndIncrease(final int times) {
        final byte previousValue = this.getValue();
        this.increase(times);
        return previousValue;
    }

    public void decrease() {
        this.setValue((byte) (this.getValue() - this.getStep()));
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     */
    public void decrease(final int times) {
        this.setValue((byte) (this.getValue() - (this.getStep() * times)));
    }

    /**
     * @return the new value decreased by one.
     */
    public byte decreaseAndGet() {
        this.decrease();
        return this.getValue();
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the new value.
     */
    public byte decreaseAndGet(final int times) {
        this.decrease(times);
        return this.getValue();
    }

    /**
     * @return the value before it gets decreased.
     */
    public byte getAndDecrease() {
        final byte previousValue = this.getValue();
        this.decrease();
        return previousValue;
    }

    /**
     * @param times is how often you wanna {@link #step} down.
     * @return the value before it gets decreased.
     */
    public byte getAndDecrease(final int times) {
        final byte previousValue = this.getValue();
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
            "ByteValue{name=%s, value=%d, min=%d, max=%d, step=%d}",
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
