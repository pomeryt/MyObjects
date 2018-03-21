package plain.value.give;

import java.util.List;

import plain.contract.give.task.TaskOfGiveable;
import plain.contract.validation.ListValidation;

/**
 * A task for strategy pattern. <br>
 * It is responsible for giving the value. <br>
 * It throws exception if the value is invalid.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to give.
 */
public final class ThrowableGive<T> implements TaskOfGiveable<T> {
	
	/**
	 * @param errorMessage to show when the value is invalid.
	 * @param validations
	 * @since 2.0.0
	 */
	@SafeVarargs
	public ThrowableGive(final String errorMessage, final ListValidation<T>... validations) {
		this.errorMessage = errorMessage;
		this.validations = validations;
	}
	
	@Override
	public T handle(final List<T> memory) {
		// Make sure the value in the memory is valid.
		for (ListValidation<T> validation : this.validations) {
			if (validation.valid(memory) == false) {
				throw new RuntimeException(this.errorMessage);
			}
		}
		
		// Return the value from the memory.
		return memory.get(0);
	}
	
	private final String errorMessage;
	private final ListValidation<T>[] validations;
}
