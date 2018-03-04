package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;
import plain.contract.validation.ValueValidation;

/**
 * Update the value. <br />
 * If new value is invalid, then it will throw Exception.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public final class ThrowableUpdate<T> implements TaskOfUpdateable<T> {

	/**
	 * @param value
	 * @param errorMessage
	 * @param validations
	 * @since 2.0.0
	 */
	@SafeVarargs
	public ThrowableUpdate(final T value, final String errorMessage, final ValueValidation<T>... validations) {
		this.value = value;
		this.errorMessage = errorMessage;
		this.validations = validations;
	}

	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Make sure the value is valid.
		for (ValueValidation<T> validation : this.validations) {
			if (validation.valid(this.value) == false) {
				throw new RuntimeException(errorMessage);
			}
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
	private final ValueValidation<T>[] validations;
}
