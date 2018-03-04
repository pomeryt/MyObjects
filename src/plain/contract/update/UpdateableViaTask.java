package plain.contract.update;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * Responsible for updating the value.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public interface UpdateableViaTask<T> {
	/**
	 * Update the value.
	 * @param task
	 * @since 1.0.0
	 */
	void update(TaskOfUpdateable<T> task);
	
	/**
	 * Add an event to be handled when the value is updated.
	 * @param event
	 * @since 1.0.0
	 */
	void addEvent(ParamEvent<T> event);
}
