package rip.lunarydess.lilith.event;

import java.util.function.Consumer;

public final class EventHandlers {
    public interface IHandler<E extends AbstractEvent> extends Consumer<E> {
        void handle(final E event);

        default @Override void accept(final E e) {
            this.handle(e);
        }

        default int priority() {
            return 0;
        }
    }

    public abstract static class AbstractHandler<E extends AbstractEvent> implements IHandler<E> {
        public abstract @Override void handle(final E event);

        public final @Override void accept(final E event) {
            this.handle(event);
        }
    }
}
