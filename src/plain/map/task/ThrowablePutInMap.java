package plain.map.task;

import java.util.Map;

import plain.contract.map.task.TaskToPutInMap;
import plain.contract.validation.MapPutValidation;

/**
 * A task for the strategy pattern. <br>
 * It is responsible for putting a pair in the map. <br>
 * It will throw exception when the map.put(K key, V value) operation is not valid.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class ThrowablePutInMap<K, V> implements TaskToPutInMap<K, V> {
	
	/**
	 * @param key of pair.
	 * @param value of pair.
	 * @param errorMessage to show when the map.put(K key, V value) operation is not valid.
	 * @param validations It checks if map.put(K key, V value) operation is valid.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public ThrowablePutInMap(final K key, final V value, final String errorMessage, final MapPutValidation<K, V>... validations) {
		this.key = key;
		this.value = value;
		this.errorMessage = errorMessage;
		this.validations = validations;
	}
	
	@Override
	public void handle(final Map<K, V> map) {
		// Make sure map.get(K key) operation is valid.
		for (MapPutValidation<K, V> validation : this.validations) {
			if (validation.valid(this.key, this.value, map) == false) {
				throw new RuntimeException(this.errorMessage);
			}
		}

		// Update the pair in map.
		map.put(this.key, this.value);
	}
	
	private final K key;
	private final V value;
	private final String errorMessage;
	private final MapPutValidation<K, V>[] validations; 
}
