package plain.value.contract;

/**
 * Responsible for giving the value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface PlainGiveable<T> {
	/**
	 * @return The value.
	 * @since 1.0.0
	 */
	T value();
}
