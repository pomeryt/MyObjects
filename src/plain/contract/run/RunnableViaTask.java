package plain.contract.run;

import plain.contract.run.task.TaskOfRunnable;

/**
 * It can run some logic via strategy pattern.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of parameter that would be used by the task.
 */
public interface RunnableViaTask<T> {
	/**
	 * @param task It has actual implementation how to execute the logic.
	 * @since 1.0.0
	 */
	void run(TaskOfRunnable<T> task);
}
