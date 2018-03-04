package plain.contract.give.task;

import java.util.List;

/**
 * A task to give a value.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public interface TaskOfGiveable<T> {
	/**
	 * @param memory The list represents the computer memory.
	 * @return A value from the memory.
	 * @since 1.0.0
	 */
	T handle(List<T> memory);
}
