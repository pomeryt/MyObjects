package plain.value;

/**
 * It decorates an {@link EventValue} object and make it thread-safe.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public final class SyncedValue<T> implements EventValue<T> {

	public SyncedValue(final EventValue<T> origin) {
		this.origin = origin;
	}
	
	@Override
	public T value() {
		synchronized (this.lock) {
			return this.origin.value();
		}
	}

	@Override
	public void update(final T value) {
		synchronized (this.lock) {
			this.origin.update(value);
		}
	}

	@Override
	public void addEvent(final UpdateEvent<T> event) {
		synchronized (this.lock) {
			this.origin.addEvent(event);
		}
	}

	private final EventValue<T> origin;
	
	private final Object lock = new Object();
}
