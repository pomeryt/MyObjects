package plain.number.count;

/**
 * A counter. <br>
 * It simply count the number one by one.
 * @author Rin
 * @version 3.0.1
 */
public final class PlainCount implements SimpleCount {

	/**
	 * The default initial value is zero.
	 * @since 1.0.0
	 */
	public PlainCount() {
		this(new int[] {0});
	}
	
	/**
	 * Define an initial value of this object.
	 * @param initialValue
	 * @since 1.1.0
	 */
	public PlainCount(final int initialValue) {
		this(new int[] {initialValue});
	}
	
	/**
	 * @param memory An integer array which contains only one value for initial count value.
	 * @since 1.0.1
	 */
	public PlainCount(final int... memory) {
		this.memory = memory.clone();
	}
	
	@Override
	public Integer value() {
		return this.memory[0];
	}

	@Override
	public void increment() {
		this.memory[0] = this.memory[0] + 1;
	}
	
	private final int[] memory;
}
