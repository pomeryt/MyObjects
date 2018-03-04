package plain.contract.validation;

import java.util.List;

/**
 * Responsible for validating the list.
 * @author Rin
 * @version 1.0.0
 * @param <T> Parameter type of the list.
 */
public interface ListValidation<T> {
	/**
	 * @param list to be validated.
	 * @return true if the list is valid.
	 * @since 1.0.0
	 */
	boolean valid(List<T> list);
}
