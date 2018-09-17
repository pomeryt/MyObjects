package plain.value;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.task.ReturnTask;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * Thread-safe version of {@link EventValue}. 
 * @author Rin
 * @version 2.0.0
 * @param <T> The type of value.
 */
public final class SyncedValue<T> implements LiveValue<T> {
	
	public SyncedValue() {
		this(new EventValue<>());
	}
	
	public SyncedValue(final T initialValue) {
		this(new EventValue<>(initialValue));
	}
	
	public SyncedValue(final LiveValue<T> liveValue) {
		this.liveValue = liveValue;
	}
	
	@Override
	public synchronized void update(final TaskOfUpdateable<T> task) {
		this.liveValue.update(task);
	}

	@Override
	public synchronized T value(final ReturnTask<T, List<T>> task) {
		return this.liveValue.value(task);
	}

	@Override
	public synchronized void update(final T value) {
		this.liveValue.update(value);
	}

	@Override
	public synchronized void addEvent(final ParamEvent<T> event) {
		this.liveValue.addEvent(event);
	}

	@Override
	public synchronized T value() {
		return this.liveValue.value();
	}
	
	private final LiveValue<T> liveValue;
}
