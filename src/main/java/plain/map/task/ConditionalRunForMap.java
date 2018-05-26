package plain.map.task;

import plain.contract.map.GiveableMap;
import plain.contract.task.VoidTask;
import plain.contract.validation.GiveableMapValidation;

/**
 * It runs Runnable object when the conditions are met. <br>
 * It is a task for the strategy pattern used by some map.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class ConditionalRunForMap<K, V> implements VoidTask<GiveableMap<K, V>> {

	/**
	 * @param runnable It has a logic to be executed when the conditions are met.
	 * @param validations Actual implementations to check the conditions.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public ConditionalRunForMap(final Runnable runnable, final GiveableMapValidation<K, V>... validations) {
		this.runnable = runnable;
		this.validations = validations;
	}
	
	@Override
	public void handle(final GiveableMap<K, V> giveableMap) {
		// Skip if the map does not meet the conditions.
		for (GiveableMapValidation<K, V> validation : this.validations) {
			if (validation.valid(giveableMap) == false) {
				return;
			}
		}
		
		// Run the logic.
		this.runnable.run();
	}
	
	private final Runnable runnable;
	private final GiveableMapValidation<K, V>[] validations;
}