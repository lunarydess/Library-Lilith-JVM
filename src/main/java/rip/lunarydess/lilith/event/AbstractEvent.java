package rip.lunarydess.lilith.event;

import rip.lunarydess.lilith.utility.interfaces.INameable;

public abstract class AbstractEvent implements INameable {
    /**
     * @implNote {@link java.util.Objects#hash(Object...)}
     * @return a valid hashcode from objects in your event.
     */
    public abstract @Override int hashCode();

    /**
     * @return comparison of hashcode plus fields.
     */
    public abstract @Override boolean equals(final Object object);
}
