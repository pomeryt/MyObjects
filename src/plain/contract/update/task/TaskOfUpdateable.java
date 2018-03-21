package plain.contract.update.task;

import java.util.List;

import plain.contract.event.ParamEvent;

/**
 * A task for strategy pattern. <br>
 * It is responsible for updating a value. <br>
 * It also use the observer pattern. <br>
 * After changing the value, it should handle all the side effects.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to update.
 */
public interface TaskOfUpdateable<T> {
	/**
	 * Update the value which is stored in the memory. <br>
	 * After updating the value, it will handle all side effects.
	 * @param memory The list represents the computer memory.
	 * @param events A list of event handlers.
	 * @since 1.0.0
	 */
	void handle(List<T> memory, List<ParamEvent<T>> events);
}
