package plain.value.give;

import java.util.List;

import plain.contract.give.task.TaskOfGiveable;

/**
 * A task for strategy pattern. <br>
 * It simply give the value without any validation. <br>
 * It should be used when the return value is expected to be valid for sure.
 * @author Rin
 * @version 2.0.1
 * @param <T> The type of value you are trying to give.
 */
public final class PlainGive<T> implements TaskOfGiveable<T> {

	@Override
	public T handle(final List<T> memory) {
		return memory.get(0);
	}
	
}
