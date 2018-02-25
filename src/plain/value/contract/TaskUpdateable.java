package plain.value.contract;

import plain.event.ParamEvent;
import plain.value.contract.task.update.UpdateTask;

/**
 * Responsible for updating the value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface TaskUpdateable<T> {
	/**
	 * Update the value.
	 * @param updateTask
	 * @since 1.0.0
	 */
	void update(UpdateTask<T> updateTask);
	
	/**
	 * Add an event to be handled when the value is updated.
	 * @param event
	 * @since 1.0.0
	 */
	void addEvent(ParamEvent<T> event);
}
