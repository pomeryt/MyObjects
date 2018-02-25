package plain.value;

import java.util.ArrayList;
import java.util.List;

import plain.event.ParamEvent;
import plain.value.contract.TaskGiveable;
import plain.value.contract.TaskUpdateable;
import plain.value.contract.task.give.GiveTask;
import plain.value.contract.task.update.UpdateTask;

/**
 * If the value is changed, then it will update everything that is related to this value. <br />
 * See <b>EventValueTest</b> for example.
 * @author Rin
 * @version 1.0.0
 */
public final class EventValue<T> implements TaskGiveable<T>, TaskUpdateable<T> {

	@Override
	public void update(final UpdateTask<T> updateTask) {
		updateTask.handle(this.memory, this.events);
	}
	
	@Override
	public void addEvent(final ParamEvent<T> event) {
		this.events.add(event);
	}

	@Override
	public T value(final GiveTask<T> giveTask) {
		return giveTask.handle(this.memory);
	}
	
	private final List<T> memory = new ArrayList<>();
	private final List<ParamEvent<T>> events = new ArrayList<>();
}
