package plain.value.give;

import java.util.List;

import plain.contract.task.ReturnTask;

/**
 * A task for strategy pattern. <br>
 * It simply give the value without any validation. <br>
 * It should be used when the return value is expected to be valid for sure.
 * @author Rin
 * @version 3.0.0
 * @param <T> The type of value you are trying to give.
 */
public final class PlainGive<T> implements ReturnTask<T, List<T>> {

	@Override
	public T handle(final List<T> memory) {
		return memory.get(0);
	}
	
}