package plain.value.contract;

import plain.value.contract.task.give.GiveTask;

/**
 * Responsible for giving the value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface TaskGiveable<T> {
	/**
	 * Give the value.
	 * @param giveTask
	 * @return a value.
	 * @since 1.0.0
	 */
	T value(GiveTask<T> giveTask);
}
