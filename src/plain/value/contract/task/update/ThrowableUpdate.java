package plain.value.contract.task.update;

import java.util.List;

import plain.event.ParamEvent;

/**
 * Update the value. <br />
 * If new value is null, then it will throw Exception. <br />
 * See <b>EventValueTest</b> for example.
 * @author Rin
 * @version 1.0.1
 * @param <T>
 */
public final class ThrowableUpdate<T> implements UpdateTask<T> {

	/**
	 * @param value New value to be updated.
	 * @param errorMessage Show this message if the value is null.
	 * @since 1.0.0
	 */
	public ThrowableUpdate(final T value, final String errorMessage) {
		this.value = value;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Make sure the value is not null.
		if (this.value == null) {
			throw new RuntimeException(errorMessage);
		}
		
		// Update value.
		memory.clear();
		memory.add(this.value);
		
		// Handle events.
		events.forEach(event -> {
			event.handle(this.value);
		});
	}
	
	private final T value;
	private final String errorMessage;
}
