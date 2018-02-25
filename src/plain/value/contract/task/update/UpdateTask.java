package plain.value.contract.task.update;

import java.util.List;

import plain.event.ParamEvent;

/**
 * A task to update the value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface UpdateTask<T> {
	/**
	 * Update the value which is stored in the memory.
	 * After updating the value, it will handle all side effects.
	 * @param memory The list represents the computer memory.
	 * @param events
	 * @since 1.0.0
	 */
	void handle(List<T> memory, List<ParamEvent<T>> events);
}
