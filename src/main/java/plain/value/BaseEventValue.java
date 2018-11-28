package plain.value;

import java.util.ArrayList;
import java.util.List;

/**
 * A primitive implementation of {@link EventValue}. <br>
 * It's not null-safe nor thread-safe.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public final class BaseEventValue<T> implements EventValue<T> {

	/**
	 * Primary constructor.
	 * @param value Initial value
	 * @since 1.0.0
	 */
	public BaseEventValue(final T value) {
		this.content = value;
	}

	@Override
	public T value() {
		return this.content;
	}

	@Override
	public void update(final T value) {
		this.events.forEach(event -> {
			event.handle(this.content, value);
		});
		this.content = value;
	}

	@Override
	public void addEvent(final UpdateEvent<T> event) {
		this.events.add(event);
	}

	private T content;

	private final List<UpdateEvent<T>> events = new ArrayList<>();
}
