package plain.loop;

import plain.contract.loop.PlainRepeatable;
import plain.contract.task.PlainTask;

/**
 * It encapsulates a Runnable and execute it repeatedly by the specified amount. <br>
 * It does not validate the amount. <br>
 * If you provide an amount that is less than 1, then it will not execute the runnable.
 * @author Rin
 * @version 1.0.1
 */
public final class PlainLoop implements PlainRepeatable {

	/**
	 * @param amount It determines how many times to execute the runnable.
	 * @param task Something to be executed in a loop.
	 * @since 1.0.0
	 */
	public PlainLoop(final int amount, final PlainTask task) {
		this.amount = amount;
		this.task = task;
	}
	
	@Override
	public void repeat() {
		for (int x = 0; x < this.amount; x++) {
			this.task.handle();
		}
	}
	
	private final int amount;
	private final PlainTask task;
}
