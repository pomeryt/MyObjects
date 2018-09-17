package plain.value;

import java.util.ArrayList;
import java.util.List;

import plain.contract.give.PlainGiveable;
import plain.contract.task.ReturnTask;

/**
 * An immutable value. <br>
 * You can use the strategy pattern when you give the value. <br>
 * In other words, you can provide a task which knows how to give the value. <br>
 * The value obtained via some logic will be cached.
 * @author Rin
 * @version 4.0.0
 * @param <T> Type of the value.
 */
public final class CachedValue<T> implements GivingValue<T> {
	
	/**
	 * @param giveable A logic to generate the value.
	 * @since 2.0.0
	 */
	public CachedValue(final PlainGiveable<T> giveable) {
		this.giveable = giveable;
	}
	
	@Override
	public T value() {
		return this.cached().get(0);
	}
	
	@Override
	public T value(final ReturnTask<T, List<T>> task) {
		return task.handle(this.cached());
	}
	
	/**
	 * @return Cached memory.
	 * @since 2.0.0
	 */
	private List<T> cached(){
		// Make sure the value has been cached.
		if (this.memory.isEmpty()) {
			memory.add(this.giveable.value());
		}
		
		// Return the cached memory.
		return this.memory;
	}
	
	private final PlainGiveable<T> giveable;
	
	private final List<T> memory = new ArrayList<>();
}
