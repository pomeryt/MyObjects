package plain.validation.value;

import plain.contract.validation.ValueValidation;

/**
 * Check if the value is not null.
 * @author Rin
 * @version 1.0.0
 * @param <T> Type of the value to be validated.
 */
public final class IsValueNullSafe<T> implements ValueValidation<T> {

	@Override
	public boolean valid(final T value) {
		return value != null;
	}
	
}
