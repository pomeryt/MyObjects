package plain.value.contract.task.give;

import java.util.List;

/**
 * Give the value. <br />
 * It throws exception if the value does not exists. <br />
 * See <b>EventValueTest</b> for example.
 * @author Rin
 * @version 1.0.1
 * @param <T>
 */
public final class ThrowableGive<T> implements GiveTask<T> {
	
	/**
	 * @param errorMessage Show this message when value does not exists.
	 * @since 1.0.0
	 */
	public ThrowableGive(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public T handle(final List<T> memory) {
		// Make sure the value exists.
		if (memory.isEmpty()) {
			throw new RuntimeException(this.errorMessage);
		}
		
		// Return value.
		return memory.get(0);
	}
	
	private final String errorMessage;
}
