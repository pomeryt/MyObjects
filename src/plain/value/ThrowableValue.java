package plain.value;

import java.util.ArrayList;
import java.util.List;

import plain.event.ParamEvent;
import plain.value.contract.PlainGiveable;
import plain.value.contract.PlainUpdateable;
import plain.value.contract.task.give.ThrowableGive;
import plain.value.contract.task.update.ThrowableUpdate;

/**
 * It is throwable version of <b>EventValue</b>. <br />
 * It will throw exceptions if the value is invalid. <br />
 * See <b>ThrowableValueTest</b> for example.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public final class ThrowableValue<T> implements PlainGiveable<T>, PlainUpdateable<T> {

	/**
	 * @param giveErrorMessage It will show this message when the value does not exist.
	 * @param updateErrorMessage It will show this message when the value is null.
	 * @since 1.0.0
	 */
	public ThrowableValue(final String giveErrorMessage, final String updateErrorMessage) {
		this.giveErrorMessage = giveErrorMessage;
		this.updateErrorMessage = updateErrorMessage;
	}
	
	@Override
	public void update(final T value) {
		new ThrowableUpdate<T>(value, this.updateErrorMessage).handle(this.memory, this.events);
	}

	@Override
	public void addEvent(final ParamEvent<T> event) {
		this.events.add(event);
	}

	@Override
	public T value() {
		return new ThrowableGive<T>(this.giveErrorMessage).handle(this.memory);
	}
	
	private final String giveErrorMessage;
	private final String updateErrorMessage;
	
	private final List<T> memory = new ArrayList<>();
	private final List<ParamEvent<T>> events = new ArrayList<>();
}
