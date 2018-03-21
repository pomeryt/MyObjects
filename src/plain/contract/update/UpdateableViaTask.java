package plain.contract.update;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * It uses strategy pattern. <br>
 * You need to provide a task in order to update the value. <br>
 * This object simply execute the task, and the task knows how to update the value. <br>
 * It also use the observer pattern. <br>
 * After changing the value, all the side effects should be handled.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to update.
 */
public interface UpdateableViaTask<T> {
	/**
	 * Execute a task in order to update.
	 * @param task It knows how to update.
	 * @since 1.0.0
	 */
	void update(TaskOfUpdateable<T> task);
	
	/**
	 * Add an event handler.
	 * @param event An event handler.
	 * @since 1.0.0
	 */
	void addEvent(ParamEvent<T> event);
}
