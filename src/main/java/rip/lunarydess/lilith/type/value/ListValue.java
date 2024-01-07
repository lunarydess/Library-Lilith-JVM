package rip.lunarydess.lilith.type.value;

import org.jetbrains.annotations.Nullable;
import rip.lunarydess.lilith.type.TypeCaster;
import rip.lunarydess.lilith.utility.ArrayKit;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ListValue<V> implements IValue {
    private final BiConsumer<V, V> onChange;
    private final List<V> values;
    private final String name;
    private V value;

    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param factory is for example '#{@link java.util.ArrayList}::new' or '#{@link java.util.concurrent.CopyOnWriteArrayList}::new'.
     * @param values is a collection of all values in this list.
     */
    @SafeVarargs
    public ListValue(final String name, final V value,
                     final Supplier<List<V>> factory,
                     final V ... values) {
        this(name, value, null, factory, values);
    }
    /**
     * @param name is the name of the value.
     * @param value is the current value.
     * @param onChange is the bi-consumer that gets applied when {@link #setValue(V)} or {@link #setValueUnchecked(Object)} gets called.
     * @param factory is for example '#{@link java.util.ArrayList}::new' or '#{@link java.util.concurrent.CopyOnWriteArrayList}::new'.
     * @param values is a collection of all values in this list.
     */
    @SafeVarargs
    public ListValue(final String name, final V value,
                     final @Nullable BiConsumer<V, V> onChange,
                     final Supplier<List<V>> factory,
                     final V ... values) {
        this.name = name;
        this.value = value;
        // @formatter:off
        this.onChange = onChange == null ? (prevV, newV) -> {} : onChange;
        // @formatter:on
        (this.values = factory.get()).addAll(Arrays.asList(values));
        this.setValue(value);
    }

    /**
     * @return the current value.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * @return a list of all values.
     */
    public List<V> getValues() {
        return this.values;
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
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format( // @formatter:off
            "ListValue{name=%s, value=%s, values=%s}",
            this.getName(),
            this.getValue(),
            ArrayKit.toString(this.getValues().toArray())
        ); // @formatter:on
    }

    public @Override int hashCode() {
        return Objects.hash( // @formatter:off
            this.getName(),
            this.getValue(),
            this.getValues()
        ); // @formatter:on
    }
}
