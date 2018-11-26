package plain2.value;

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
		synchronized (this) {
			return this.origin.value();
		}
	}

	@Override
	public void update(final T value) {
		synchronized (this) {
			this.origin.update(value);
		}
	}

	@Override
	public void addEvent(final UpdateEvent<T> event) {
		synchronized (this) {
			this.origin.addEvent(event);
		}
	}

	private final EventValue<T> origin;
}
