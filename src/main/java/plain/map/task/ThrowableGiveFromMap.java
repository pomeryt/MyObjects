package plain.map.task;

import java.util.Map;

import plain.contract.task.ReturnTask;
import plain.contract.validation.MapGetValidation;

/**
 * A task for the strategy pattern. <br>
 * It is responsible for giving a value from the map. <br>
 * It will throw exception when the map.get(K key) operation is not valid.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class ThrowableGiveFromMap<K, V> implements ReturnTask<V, Map<K, V>> {
	
	/**
	 * @param key to get value from the map.
	 * @param errorMessage to show when the map.get(K key) operation is not valid.
	 * @param validations It checks if map.get(K key) operation is valid.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public ThrowableGiveFromMap(final K key, final String errorMessage, final MapGetValidation<K, V>... validations) {
		this.key = key;
		this.errorMessage = errorMessage;
		this.validations = validations.clone();
	}
	
	@Override
	public V handle(final Map<K, V> map) {
		// Make sure map.get(K key) operation is valid.
		for (final MapGetValidation<K, V> validation : this.validations) {
			if (validation.valid(this.key, map) == false) {
				throw new RuntimeException(this.errorMessage);
			}
		}
		
		// Return the value from the map.
		return map.get(this.key);
	}
	
	private final K key;
	private final String errorMessage;
	private final MapGetValidation<K, V>[] validations; 
}
