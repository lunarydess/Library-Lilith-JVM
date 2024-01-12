package rip.lunarydess.lilith.type.value.primitive;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.consumer.bi.primitive.Char2CharBiConsumer;
import rip.lunarydess.lilith.type.value.IValue;

import java.util.Objects;

public class CharValue implements IValue {
    // @formatter:off
    private static final Char2CharBiConsumer EMPTY_CONSUMER = (prevV, newV) -> {};
    // @formatter:on
    private final Char2CharBiConsumer onChange;
    private final String name;
    private char value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     */
    public CharValue(final String name, final char value) {
        this(name, value, null);
    }

    /**
     * 
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the primitive-bi-consumer that gets applied when {@link #setValue(char)} or {@link #setValueUnchecked(Object)} gets called.
     */
    public CharValue(final String name, final char value,
                     final @Nullable Char2CharBiConsumer onChange) {
        this.name = name;
        this.value = value;
        this.onChange = onChange == null ? EMPTY_CONSUMER : onChange;
        this.setValue(value);
    }

    /**
     * @return the current value.
     */
    public char getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(final char value) {
        this.onChange.acceptChar(this.value, this.value = value);
    }

    /**
     * @apiNote please use {@link #setValue(char)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue((char) value);
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
            "CharValue{name=%s, value=%c}",
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
