package rip.lunarydess.lilith.event;

import rip.lunarydess.lilith.utility.ArrayKit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EventManager {
    private static final Consumer<Throwable> DEFAULT_ON_ERROR = Throwable::printStackTrace;

    private final Map<Class<? extends AbstractEvent>, EventHandlers.IHandler<? extends AbstractEvent>[]> handlers;

    private final Consumer<Throwable> onError;

    // @formatter:off
    public EventManager() { this(HashMap::new, DEFAULT_ON_ERROR); }

    /**
     * @param factory e.g. {@link HashMap#HashMap() HashMap::new}
     *                or {@link ConcurrentHashMap#ConcurrentHashMap() ConcurrentHashMap::new}.
     */
    public EventManager(final Supplier<Map<Class<? extends AbstractEvent>,
                    EventHandlers.IHandler<? extends AbstractEvent>[]
                    >> factory) { this(factory, DEFAULT_ON_ERROR); }
    // @formatter:on

    /**
     * @param factory e.g. {@link HashMap#HashMap() HashMap::new}
     *                or {@link ConcurrentHashMap#ConcurrentHashMap() ConcurrentHashMap::new}.
     * @param onError e.g. {@link Throwable#printStackTrace() Throwable::printStackTrace}
     */
    public EventManager(
            final Supplier<Map<Class<? extends AbstractEvent>, EventHandlers.IHandler<? extends AbstractEvent>[]>> factory,
            final Consumer<Throwable> onError
    ) {
        this.handlers = factory.get();
        this.onError = onError;
    }

    @SuppressWarnings("unchecked")
    public <H extends EventHandlers.IHandler<E>, E extends AbstractEvent>
    void register(final Class<E> clazz, final H handler) { // @formatter:off
        try { this.handlers.put(clazz, Arrays.stream(ArrayKit.add(
                            EventHandlers.IHandler[]::new,
                            this.handlers.getOrDefault(clazz, new EventHandlers.IHandler[0]),
                            handler
                    )).sorted(Comparator.comparingInt(value -> -value.priority()))
                    .toArray(EventHandlers.IHandler[]::new)); }
        catch (final Throwable throwable) { onError.accept(throwable); }
    } // @formatter:om

    @SuppressWarnings("unchecked")
    public <H extends EventHandlers.IHandler<E>, E extends AbstractEvent>
    void unregister(final Class<E> clazz, final H handler) { // TODO: ... | test this method, too tired rn. >.>
        final var handlers = this.handlers.getOrDefault(clazz, new EventHandlers.IHandler[0]);
        if (handlers.length == 0) {
            this.handlers.remove(clazz);
            return;
        }
        final int index = ArrayKit.indexOf(handlers, handler);
        if (index == -1) {
            onError.accept(new NoSuchFieldError(String.format("the handler %s doesn't exist, couldn't be removed properly.", handler.toString())));
            return;
        }
        this.handlers.put(clazz, ArrayKit.removeAt(EventHandlers.IHandler[]::new, handlers, index));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <E extends AbstractEvent>
    void call(final E event) {
        for (final EventHandlers.IHandler iHandler : this.handlers.get(event.getClass())) { // @formatter:off
            try                               { iHandler.accept(event    ); }
            catch (final Throwable throwable) { onError .accept(throwable); }
        } // @formatter:on
    }
}
