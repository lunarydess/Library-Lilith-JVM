package rip.lunarydess.lilith.type.value;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.TypeCaster;

import java.util.Objects;
import java.util.function.BiConsumer;

public class GenericValue<V> implements IValue {
    private final BiConsumer<V, V> onChange;
    private final String name;
    private V value;

    /**
     * @param name  is the name of the value.
     * @param value is the current value.
     */
    public GenericValue(final String name, final V value) {
        this(name, value, null);
    }

    /**
     * @param name     is the name of the value.
     * @param value    is the current value.
     * @param onChange is the bi-consumer that gets applied when {@link #setValue(V)} or {@link #setValueUnchecked(Object)} gets called.
     */
    public GenericValue(final String name, final V value,
                        final @Nullable BiConsumer<V, V> onChange) {
        this.name = name;
        this.value = value;
        // @formatter:off
        this.onChange = onChange == null ? (prevV, newV) -> {} : onChange;
        // @formatter:on
        this.setValue(value);
    }

    /**
     * @return the current value.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * @param value will call {@link #onChange} and replace {@link #value}.
     */
    public void setValue(final V value) {
        this.onChange.accept(this.value, this.value = value);
    }

    /**
     * @apiNote please use {@link #setValue(V)} if possible! :(
     */
    @Deprecated
    public @Override void setValueUnchecked(final Object value) throws ClassCastException {
        this.setValue(TypeCaster.cast(value));
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
        return String.format( // @formatter:off
            "GenericValue{name=%s, value=%s}",
            this.getName(),
            this.getValue()
        ); // @formatter:on
    }

    public @Override int hashCode() {
        return Objects.hash( // @formatter:off
            this.getName(),
            this.getValue()
        ); // @formatter:on
    }
}
