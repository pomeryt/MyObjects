package plain.loop;

import plain.contract.loop.PlainRepeatable;

/**
 * It encapsulates a Runnable and execute it repeatedly by the specified amount. <br>
 * It does not validate the amount. <br>
 * If you provide an amount that is less than 1, then it will not execute the runnable.
 * @author Rin
 * @version 1.0.0
 */
public final class PlainLoop implements PlainRepeatable {

	/**
	 * @param amount It determines how many times to execute the runnable.
	 * @param runnable Something to be executed in a loop.
	 * @since 1.0.0
	 */
	public PlainLoop(final int amount, final Runnable runnable) {
		this.amount = amount;
		this.runnable = runnable;
	}
	
	@Override
	public void repeat() {
		for (int x = 0; x < this.amount; x++) {
			this.runnable.run();
		}
	}
	
	private final int amount;
	private final Runnable runnable;
}
