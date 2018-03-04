package plain.value;

import java.util.ArrayList;
import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.give.GiveableViaTask;
import plain.contract.give.PlainGiveable;
import plain.contract.give.task.TaskOfGiveable;
import plain.contract.update.PlainUpdateable;
import plain.contract.update.UpdateableViaTask;
import plain.contract.update.task.TaskOfUpdateable;
import plain.value.give.PlainGive;
import plain.value.update.PlainUpdate;

/**
 * If the value is changed, then it will update everything that is related to this value. <br />
 * See <b>EventValueTest</b> for example.
 * @author Rin
 * @version 2.0.0
 */
public final class EventValue<T> implements PlainGiveable<T>, PlainUpdateable<T>, GiveableViaTask<T>, UpdateableViaTask<T> {

	@Override
	public T value() {
		return new PlainGive<T>().handle(this.memory);
	}
	
	@Override
	public void update(final T value) {
		new PlainUpdate<>(value).handle(this.memory, this.events);;
	}
	
	@Override
	public void update(final TaskOfUpdateable<T> updateTask) {
		updateTask.handle(this.memory, this.events);
	}
	
	@Override
	public void addEvent(final ParamEvent<T> event) {
		this.events.add(event);
	}

	@Override
	public T value(final TaskOfGiveable<T> giveTask) {
		return giveTask.handle(this.memory);
	}
	
	private final List<T> memory = new ArrayList<>();
	private final List<ParamEvent<T>> events = new ArrayList<>();
}
