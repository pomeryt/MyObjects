package plain.value.validation;

import java.util.List;

import plain.contract.validation.ListValidation;

/**
 * Check if the list if null-free.
 * @author Rin
 * @version 1.0.0
 * @param <T> Parameter type of the list.
 */
public final class IsListNullSafe<T> implements ListValidation<T> {

	@Override
	public boolean valid(final List<T> list) {
		// Return false if the list has null.
		for (T item : list) {
			if (item == null) {
				return false;
			}
		}
		
		// Return true if the list is null-free.
		return true;
	}
	
}
