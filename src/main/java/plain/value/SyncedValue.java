package plain.value;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.give.GiveableViaTask;
import plain.contract.give.PlainGiveable;
import plain.contract.task.ReturnTask;
import plain.contract.update.PlainUpdateable;
import plain.contract.update.UpdateableViaTask;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * Thread-safe version of {@link EventValue}. 
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public final class SyncedValue<T> implements PlainGiveable<T>, PlainUpdateable<T>, GiveableViaTask<T, List<T>>, UpdateableViaTask<T> {
	
	public SyncedValue() {
		this(new EventValue<>());
	}
	
	public SyncedValue(final T initialValue) {
		this(new EventValue<>(initialValue));
	}
	
	public SyncedValue(final EventValue<T> eventValue) {
		this.eventValue = eventValue;
	}
	
	@Override
	public synchronized void update(final TaskOfUpdateable<T> task) {
		this.eventValue.update(task);
	}

	@Override
	public synchronized T value(final ReturnTask<T, List<T>> task) {
		return this.eventValue.value(task);
	}

	@Override
	public synchronized void update(final T value) {
		this.eventValue.update(value);
	}

	@Override
	public synchronized void addEvent(final ParamEvent<T> event) {
		this.eventValue.addEvent(event);
	}

	@Override
	public synchronized T value() {
		return this.eventValue.value();
	}
	
	private final EventValue<T> eventValue;
}
