package plain.validation.list;

import java.util.List;

import plain.contract.validation.ListValidation;

/**
 * Check if the list contains a value. <br>
 * In other words, it checks if the list is not empty.
 * @author Rin
 * @version 1.0.0
 * @param <T> Parameter type of the list.
 */
public final class IsListFilled<T> implements ListValidation<T> {

	@Override
	public boolean valid(final List<T> list) {
		return !list.isEmpty();
	}

}
