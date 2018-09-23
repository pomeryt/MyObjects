package plain.value.give;

import java.util.List;

import plain.contract.task.ReturnTask;
import plain.contract.validation.ListValidation;

/**
 * A task object of GiveableViaTask object. <br>
 * It gives default value if it fails to pass all validations.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of return value.
 */
public final class DefaultableGive<T> implements ReturnTask<T, List<T>> {

	/**
	 * @param defaultValue to return when the value is invalid.
	 * @param validations It will return default value when it fails to pass all validations.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public DefaultableGive(final T defaultValue, final ListValidation<T>...validations) {
		this.defaultValue = defaultValue;
		this.validations = validations;
	}
	
	@Override
	public T handle(final List<T> memory) {
		// Make sure the value in the memory is valid.
		for (final ListValidation<T> validation : this.validations) {
			if (validation.valid(memory) == false) {
				return this.defaultValue;
			}
		}

		// Return the value from the memory.
		return memory.get(0);
	}
	
	private final T defaultValue;
	private final ListValidation<T>[] validations;
}
