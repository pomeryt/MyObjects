package plain.value.contract.task.give;

import java.util.List;

/**
 * It simply give the value without any validation. <br />
 * It should be used when the return value is expected to be valid for sure. <br />
 * See <b>PlainTaskValueTest</b> for example.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public final class PlainGive<T> implements GiveTask<T> {

	@Override
	public T handle(final List<T> memory) {
		return memory.get(0);
	}
	
}
