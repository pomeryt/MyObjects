package plain.contract.update;

import plain.contract.event.ParamEvent;

/**
 * Responsible for updating the value. <br>
 * It uses observer pattern. <br>
 * After changing the value, all the side effects should be handled.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to update.
 */
public interface PlainUpdateable<T> {
	/**
	 * Update the value and handle all events.
	 * @param value New value.
	 * @since 1.0.0
	 */
	void update(T value);
	
	/**
	 * @param event An event handler.
	 * @since 1.0.0
	 */
	void addEvent(ParamEvent<T> event);
}
