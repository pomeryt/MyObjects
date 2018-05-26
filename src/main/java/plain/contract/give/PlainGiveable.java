package plain.contract.give;

/**
 * Responsible for giving the value.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to give.
 */
public interface PlainGiveable<T> {
	/**
	 * @return The value.
	 * @since 1.0.0
	 */
	T value();
}
