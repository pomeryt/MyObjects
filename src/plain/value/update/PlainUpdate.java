package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * A task for strategy pattern. <br>
 * It simply update the value without any validation. <br>
 * It should be used when new value is expected to be valid for sure. <br>
 * It also use the observer pattern. <br>
 * After changing the value, it should handle all the side effects.
 * @author Rin
 * @version 2.0.1
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
