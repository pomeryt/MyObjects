package plain.value;

import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.task.ReturnTask;
import plain.contract.update.task.TaskOfUpdateable;

/**
 * Thread-safe version of {@link EventValue}. 
 * @author Rin
 * @version 2.0.1
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
	public void update(final TaskOfUpdateable<T> task) {
		synchronized (this) {
			this.liveValue.update(task);
		}
	}

	@Override
	public T value(final ReturnTask<T, List<T>> task) {
		synchronized (this) {
			return this.liveValue.value(task);
		}
	}

	@Override
	public void update(final T value) {
		synchronized (this) {
			this.liveValue.update(value);
		}
	}

	@Override
	public void addEvent(final ParamEvent<T> event) {
		synchronized (this) {
			this.liveValue.addEvent(event);
		}
	}

	@Override
	public T value() {
		synchronized (this) {
			return this.liveValue.value();
		}
	}
	
	private final LiveValue<T> liveValue;
}
