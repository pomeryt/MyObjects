package javafx.contract;

import javafx.contract.task.TaskOfDoableWithNode;

/**
 * It is responsible for executing a task. <br>
 * In other words, it uses the strategy pattern. <br>
 * This object should have JavaFX node object so the task can do something with it.
 * @author Rin
 * @version 1.0.0
 */
public interface DoableWithNodeViaTask {
	/**
	 * @param task It has actual implementation of what you want to do.
	 * @since 1.0.0
	 */
	void execute(TaskOfDoableWithNode task);
}
