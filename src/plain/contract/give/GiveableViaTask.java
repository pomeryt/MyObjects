package plain.contract.give;

import plain.contract.give.task.TaskOfGiveable;

/**
 * It uses strategy pattern. <br>
 * You need to provide a task in order to give the value. <br>
 * This object simply execute the task, and the task knows how to give the value.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying give.
 */
public interface GiveableViaTask<T> {
	/**
	 * Give the value.
	 * @param task
	 * @return a value.
	 * @since 1.0.0
	 */
	T value(TaskOfGiveable<T> task);
}
