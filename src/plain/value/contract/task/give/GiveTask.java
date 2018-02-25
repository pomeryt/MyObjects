package plain.value.contract.task.give;

import java.util.List;

/**
 * A task to give a value.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface GiveTask<T> {
	/**
	 * @param memory The list represents the computer memory.
	 * @return A value from the memory.
	 * @since 1.0.0
	 */
	T handle(List<T> memory);
}
