package rip.lunarydess.lilith.utility.interfaces;

import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface ICopyable<C extends ICopyable<C>> {
    /**
     * @return a copy of the given object.
     * @implNote don't forget to set all fields in your new copy properly!
     */
    @Nullable
    C copy();
}
