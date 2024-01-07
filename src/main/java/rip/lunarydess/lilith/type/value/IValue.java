package rip.lunarydess.lilith.type.value;

import rip.lunarydess.lilith.utility.interfaces.INameable;

public interface IValue extends INameable {
    /**
     * @param value is the expected new value.
     * @throws ClassCastException if value ain't valid.
     */
    @Deprecated
    void setValueUnchecked(final Object value) throws ClassCastException;
}
