package plain.contract.give.task;

import java.util.List;

/**
 * A task for strategy pattern. <br>
 * It is responsible for giving a value.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to give.
 */
public interface TaskOfGiveable<T> {
	/**
	 * @param memory The list represents the computer memory.
	 * @return A value from the memory.
	 * @since 1.0.0
	 */
	T handle(List<T> memory);
}
