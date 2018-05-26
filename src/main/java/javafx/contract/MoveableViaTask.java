package javafx.contract;

import javafx.contract.task.TaskOfMoveable;

/**
 * It is responsible for executing a task. <br>
 * In other words, it uses the strategy pattern. <br>
 * The task should have actual implementation of moving logic.
 * @author Rin
 * @version 1.0.0
 */
public interface MoveableViaTask {
	/**
	 * @param task It has actual implementation of movign logic.
	 * @since 1.0.0
	 */
	void move(TaskOfMoveable task);
}
