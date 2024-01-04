package rip.lunarydess.lilith.type;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public final class TypeCaster {
    TypeCaster() {}

    /**
     * @param <T>    the type we want to cast to
     * @param object the object we want to cast
     * @return the cast object (if possible)
     */
    @Contract("null->null;!null->!null")
    @SuppressWarnings("unchecked")
    public @Nullable
    static <T> T cast(final @Nullable Object object) {
        return (T) object;
    }
}
