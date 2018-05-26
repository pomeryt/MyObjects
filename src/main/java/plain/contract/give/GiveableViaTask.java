package plain.contract.give;

import plain.contract.task.ReturnTask;

/**
 * This object can give you something by executing some task. <br>
 * You need to provide a task in order to give the value. <br>
 * This object simply execute the task, and the task knows how to give the value.
 * @author Rin
 * @version 3.0.0
 * @param <R> The type of value you are trying give.
 * @param <P> The type of parameter for the task object.
 */
public interface GiveableViaTask<R, P> {
	/**
	 * @param task It has actual implementation for giving a value.
	 * @return Something from the task.
	 * @since 3.0.0
	 */
	R value(ReturnTask<R, P> task);
}
