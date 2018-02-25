package plain.value.contract;

import plain.event.ParamEvent;

/**
 * Responsible for updating the value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface PlainUpdateable<T> {
	/**
	 * Update the value and handle all events.
	 * @param value
	 * @since 1.0.0
	 */
	void update(T value);
	
	/**
	 * @param event It will be handled when the vlaue is updated.
	 * @since 1.0.0
	 */
	void addEvent(ParamEvent<T> event);
}
