package plain.value;

/**
 * A value with observer pattern.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public interface EventValue<T> {
	/**
	 * @return The value.
	 * @since 1.0.0
	 */
	T value();
	
	/**
	 * Change the value. <br>
	 * It will handle events before changing the value.
	 * @param value New value.
	 * @since 1.0.0
	 */
	void update(T value);
	
	/**
	 * @param event An event to be handled when the value is about to be changed.
	 * @since 1.0.0
	 */
	void addEvent(UpdateEvent<T> event);
}
