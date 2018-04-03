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
import plain.value.give.ThrowableGive;
import plain.value.update.PlainUpdate;
import plain.value.validation.IsListFilled;

/**
 * A mutable value. <br>
 * You can apply the strategy pattern. <br>
 * For example, you can provide a task which knows how to give or update the value. <br>
 * It also use the observer pattern. <br>
 * If the value is changed, then it will update everything that is related to this value.
 * @author Rin
 * @version 2.0.2
 */
public final class EventValue<T> implements PlainGiveable<T>, PlainUpdateable<T>, GiveableViaTask<T>, UpdateableViaTask<T> {

	@Override
	public T value() {
		return new ThrowableGive<T>("The value does not exist. Please update the value first.", new IsListFilled<>()).handle(this.memory);
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
