package plain.validation.list;

import java.util.List;

import plain.contract.validation.ListValidation;

/**
 * Check if the list is null-free.
 * @author Rin
 * @version 1.0.1
 * @param <T> Parameter type of the list.
 */
public final class IsListNullSafe<T> implements ListValidation<T> {

	@Override
	public boolean valid(final List<T> list) {
		// Return false if the list has null.
		for (final T item : list) {
			if (item == null) {
				return false;
			}
		}
		
		// Return true if the list is null-free.
		return true;
	}
	
}
