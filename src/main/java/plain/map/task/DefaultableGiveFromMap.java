package plain.map.task;

import java.util.Map;

import plain.contract.task.ReturnTask;
import plain.contract.validation.MapGetValidation;

/**
 * A task object of GiveableMap object. <br>
 * It returns default value if it fails to pass all validations.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key in map.
 * @param <V> The type of value in map.
 */
public final class DefaultableGiveFromMap<K, V> implements ReturnTask<V, Map<K, V>> {
	
	/**
	 * @param key to get value in the map.
	 * @param defaultValue to return if it fails to pass all validations.
	 * @param validations 
	 * @since 1.0.0
	 */
	@SafeVarargs
	public DefaultableGiveFromMap(final K key, final V defaultValue, final MapGetValidation<K, V>...validations) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.validations = validations.clone();
	}
	
	@Override
	public V handle(final Map<K, V> map) {
		// Make sure map.get(K key) operation is valid.
		for (final MapGetValidation<K, V> validation : this.validations) {
			if (!validation.valid(this.key, map)) {
				return this.defaultValue;
			}
		}
		
		// Return the value from the map.
		return map.get(this.key);
	}
	
	private final K key;
	private final V defaultValue;
	private final MapGetValidation<K, V>[] validations;
}
