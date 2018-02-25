package plain.value;

import plain.value.contract.PlainGiveable;

/**
 * The value obtained via some logic will be cached. <br />
 * It will throw exceptions if the value is invalid. <br />
 * See <b>CachedValueTest</b> for example.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public final class CachedValue<T> implements PlainGiveable<T> {
	
	/**
	 * Make sure the logic does not return null.
	 * @param giveable A logic to obtain a value initially.
	 * @since 1.0.0
	 */
	public CachedValue(final PlainGiveable<T> giveable) {
		this(giveable, new ThrowableValue<>(
				"You should not see this error message because you will see update-error message before. "
				+"Provide ThrowableValue object to the constructor of this object and define a meaningful error message.",
				"The provided giveable returns null. Please make sure it returns something valid. "
				+"Perhaps providing ThrowableValue object to the constructor of this object may help you to get more meaningful error message."
				));
	}
	
	/**
	 * Use this constructor to define more meaningful error message in case the logic returns null.
	 * @param giveable A logic to obtain a value initially.
	 * @param cache A value obtained by the <b>giveable</b> will be stored in this object.
	 * @since 1.0.0
	 */
	public CachedValue(final PlainGiveable<T> giveable, final ThrowableValue<T> cache) {
		this.giveable = giveable;
		this.cache = cache;
		
		cache.addEvent(value -> {
			this.cached[0] = true;
		});
	}
	
	@Override
	public T value() {
		if (this.cached[0] == false) {
			this.cache.update(this.giveable.value());
		}
		
		return this.cache.value();
	}
	
	private final PlainGiveable<T> giveable;
	private final ThrowableValue<T> cache;
	
	private final boolean[] cached = {false};
}
