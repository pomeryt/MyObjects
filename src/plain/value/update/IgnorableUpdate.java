package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;
import plain.contract.validation.ValueValidation;

/**
 * Update the value. <br />
 * If new value is null, then it will do nothing. <br />
 * See <b>IgnorableUpdateTest</b> for example.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public final class IgnorableUpdate<T> implements TaskOfUpdateable<T> {

	/**
	 * @param value
	 * @param validations
	 * @since 2.0.0
	 */
	@SafeVarargs
	public IgnorableUpdate(final T value, final ValueValidation<T>... validations) {
		this.value = value;
		this.validations = validations;
	}

	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Make sure the value is valid.
		for (ValueValidation<T> validation : this.validations) {
			if (validation.valid(this.value) == false) {
				return;
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
	private final ValueValidation<T>[] validations;
}
