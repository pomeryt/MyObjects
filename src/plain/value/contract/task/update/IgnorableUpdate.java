package plain.value.contract.task.update;

import java.util.List;

import plain.event.ParamEvent;

/**
 * Update the value. <br />
 * If new value is null, then it will do nothing. <br />
 * See <b>IgnorableUpdateTest</b> for example.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public final class IgnorableUpdate<T> implements UpdateTask<T> {

	/**
	 * @param value New value to be updated.
	 * @since 1.0.0
	 */
	public IgnorableUpdate(final T value) {
		this.value = value;
	}

	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Do nothing if the value is null.
		if (this.value == null) {
			return;
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
}
