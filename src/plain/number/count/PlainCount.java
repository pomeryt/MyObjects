package plain.number.count;

import plain.number.count.contract.PlainIncrementable;
import plain.number.count.contract.PlainIntGivable;

/**
 * It simply count integer. <br />
 * See <b>PlainCountTest</b> for example.
 * @author Rin
 * @version 1.0.0
 */
public final class PlainCount implements PlainIncrementable, PlainIntGivable {

	/**
	 * The default initial value is zero.
	 * @since 1.0.0
	 */
	public PlainCount() {
		this(new int[] {0});
	}
	
	/**
	 * @param memory An integer array which contains only one value for initial count value.
	 * @since 1.0.0
	 */
	public PlainCount(final int[] memory) {
		this.memory = memory;
	}
	
	@Override
	public int value() {
		return this.memory[0];
	}

	@Override
	public void increment() {
		this.memory[0] = this.memory[0] + 1;
	}
	
	private final int[] memory;
}
