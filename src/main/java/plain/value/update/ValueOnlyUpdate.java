package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;
import plain.contract.validation.ValueValidation;
import plain.validation.value.IsValueNullSafe;

/**
 * It changes the value without handling any events. <br>
 * You may optionally specify some validations via constructor. <br>
 * It will throw an exception when the value is invalid. <br>
 * By default, it will validate the value to make sure it is not null. <br>
 * The default validation will not be applied in case you specify. <br>
 * That means you need to explicitly provide null-safe validation if you want to.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public final class ValueOnlyUpdate<T> implements TaskOfUpdateable<T> {
	
	/**
	 * Secondary constructor. <br>
	 * It will throw an exception if the value is null. <br>
	 * @param value The value will be changed to this one.
	 * @since 1.0.0
	 */
	public ValueOnlyUpdate(final T value) {
		this(value, "You cannot change the value to null.", new IsValueNullSafe<>());
	}
	
	/**
	 * Primary constructor. <br>
	 * You can specify some validations.
	 * @param value The value will be changed to this one.
	 * @param errorMessage It will show this message if the value is invalid.
	 * @param validations It will validate the value.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public ValueOnlyUpdate(final T value, final String errorMessage, final ValueValidation<T>... validations) {
		this.value = value;
		this.errorMessage = errorMessage;
		this.validations = validations.clone();
	}
	
	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Make sure the value is valid.
		for (final ValueValidation<T> validation : this.validations) {
			if (validation.valid(this.value) == false) {
				throw new RuntimeException(errorMessage);
			}
		}

		// Update value.
		memory.clear();
		memory.add(this.value);
	}
	
	private final T value;
	private final String errorMessage;
	private final ValueValidation<T>[] validations;

}
