package plain.contract.validation;

/**
 * Responsible for validating the value.
 * @author Rin
 * @version 1.0.0
 * @param <T> Type of the value to be validated.
 */
public interface ValueValidation<T> {
	/**
	 * @param value to be validated.
	 * @return true if the value is valid.
	 * @since 1.0.0
	 */
	boolean valid(T value);
}
