package rip.lunarydess.lilith.type.value.primitive;

import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.Bool2BoolBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class BooleanValue implements IValue {
    // @formatter:off
    private static final Bool2BoolBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on
    private final Bool2BoolBiConsumer onChange;
    private final String name;
    private boolean value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    public BooleanValue(final String name, final boolean value) {
        this(name, value, null);
    }

    /**
     * 
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(boolean)} or {@link #setValueUnchecked(Object)} gets called.
     */
    public BooleanValue(final String name, final boolean value,
                        final @Nullable Bool2BoolBiConsumer onChange) {
        this.name = name;
        this.value = value;
        this.onChange = onChange == null ? EMPTY_CONSUMER : onChange;
        this.setValue(value);
    }

    /**
     * @return the current value.
     */
    public boolean getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(final boolean value) {
        this.onChange.acceptBool(this.value, this.value = value);
    }

    /**
     * @apiNote please use {@link #setValue(boolean)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((boolean) value);
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
            "BooleanValue{name=%s, value=%b}",
            this.getName(),
            this.getValue()
        );
    }

    public @Override int hashCode() {
        return Objects.hash(
            this.getName(),
            this.getValue()
        );
    }
}
