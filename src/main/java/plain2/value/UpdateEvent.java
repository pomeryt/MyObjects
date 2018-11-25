package plain2.value;

/**
 * An event where the value is about to be updated.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public interface UpdateEvent<T> {
	void handle(T oldValue, T newValue);
}
