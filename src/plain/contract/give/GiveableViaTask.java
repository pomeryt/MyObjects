package plain.contract.give;

import plain.contract.give.task.TaskOfGiveable;

/**
 * Responsible for giving the value.
 * @author Rin
 * @version 2.0.0
 * @param <T>
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
