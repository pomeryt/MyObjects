package plain.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import plain.contract.event.ParamEvent;
import plain.contract.give.GiveableViaTask;
import plain.contract.give.PlainGiveable;
import plain.contract.give.task.TaskOfGiveable;
import plain.contract.update.PlainUpdateable;
import plain.contract.update.UpdateableViaTask;
import plain.contract.update.task.TaskOfUpdateable;
import plain.validation.list.IsListFilled;
import plain.value.give.ThrowableGive;
import plain.value.update.PlainUpdate;

/**
 * A mutable value. <br>
 * You can apply the strategy pattern. <br>
 * For example, you can provide a task which knows how to give or update the value. <br>
 * It also use the observer pattern. <br>
 * If the value is changed, then it will update everything that is related to its value. <br><br>
 * Internally, it encapsulates the List&#60;T&#62; object to store its value. <br>
 * Conceptually, the list is considered as computer memory, and this object is responsible for interacting with the memory for you. <br>
 * Only the first element of the list should be used to store its value. <br>
 * In other words, only index 0 of the list should be used.
 * @author Rin
 * @version 2.1.0
 */
public final class EventValue<T> implements PlainGiveable<T>, PlainUpdateable<T>, GiveableViaTask<T>, UpdateableViaTask<T> {

	/**
	 * Instantiate this object without initial value.
	 * @since 2.1.1
	 */
	public EventValue() {
		this(new ArrayList<T>());
	}
	
	/**
	 * Instantiate this object with initial value.
	 * @param initialValue
	 * @since 2.1.0
	 */
	public EventValue(final T initialValue) {
		this(new ArrayList<T>(Arrays.asList(initialValue)));
	}
	
	/**
	 * A primary constructor.
	 * @param memory The list represents the computer memory.
	 * @since 2.1.0
	 */
	public EventValue(final List<T> memory) {
		this.memory = memory;
	}
	
	@Override
	public T value() {
		return new ThrowableGive<T>("The value does not exist. Please update the value first.", new IsListFilled<>()).handle(this.memory);
	}
	
	@Override
	public void update(final T value) {
		new PlainUpdate<>(value).handle(this.memory, this.events);
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
	
	private final List<T> memory;
	private final List<ParamEvent<T>> events = new ArrayList<>();
}
