package plain.value.give;

import java.util.List;

import plain.contract.task.ReturnTask;

/**
 * It returns the default value when no value is stored in the memory. <br><br>
 * Unfortunately, it may return null under following conditions: 
 * 1. null is stored in the memory.
 * 2. The default value is null.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of return value.
 */
public final class GiveDefaultIfEmpty<T> implements ReturnTask<T, List<T>> {

	/**
	 * @param defaultValue to return when the memory is empty.
	 * @since 1.0.0
	 */
	public GiveDefaultIfEmpty(final T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public T handle(final List<T> memory) {
		if (memory.isEmpty()) {
			return this.defaultValue;
		}
		return memory.get(0);
	}
	
	private final T defaultValue;
}