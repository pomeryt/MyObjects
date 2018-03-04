package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * It simply update the value without any validation. <br />
 * It should be used when the input value is expected to be valid for sure. <br />
 * It is still responsible for handling the events. <br />
 * See <b>PlainTaskValueTest</b> for example.
 * @author Rin
 * @version 2.0.0
 */
public final class PlainUpdate<T> implements TaskOfUpdateable<T> {

	/**
	 * @param value New value to be updated.
	 * @since 1.0.0
	 */
	public PlainUpdate(final T value) {
		this.value = value;
	}
	
	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		// Update the value.
		memory.clear();
		memory.add(value);
		
		// Handle events.
		events.forEach(event -> {
			event.handle(value);
		});
	}
	
	private final T value;
}
