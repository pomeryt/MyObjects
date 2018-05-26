package plain.value.update;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * It triggers the event-handlers without changing the value. <br>
 * All events will be handled based on the value already in the memory.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public final class EventOnlyUpdate<T> implements TaskOfUpdateable<T> {

	@Override
	public void handle(final List<T> memory, final List<ParamEvent<T>> events) {
		events.forEach(event -> {
			event.handle(memory.get(0));
		});
	}
	
}
