package plain.value.give;

import java.util.List;

import plain.contract.give.task.TaskOfGiveable;

/**
 * It simply give the value without any validation. <br />
 * It should be used when the return value is expected to be valid for sure. <br />
 * See <b>PlainTaskValueTest</b> for example.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public final class PlainGive<T> implements TaskOfGiveable<T> {

	@Override
	public T handle(final List<T> memory) {
		return memory.get(0);
	}
	
}
