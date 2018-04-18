package plain.contract.run.task;

/**
 * A task for the strategy pattern. <br>
 * It should run a Runnable object.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of parameter that would be used with Runnable object.
 */
public interface TaskOfRunnable<T> {
	/**
	 * Execute the Runnable object.
	 * @param param Anything that is needed for executing the Runnable object.
	 * @since 1.0.0
	 */
	void handle(T param);
}
