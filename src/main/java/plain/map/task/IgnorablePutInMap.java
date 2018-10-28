package plain.map.task;

import java.util.Map;

import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.task.VoidTask;
import plain.contract.validation.MapPutValidation;

/**
 * A task object of {@link RegisterableMap} or {@link UpdateableMap} object. <br>
 * It tries to put a pair to the map. <br>
 * However, it will do nothing if it fails to pass all validations.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key in map.
 * @param <V> The type of value in map.
 */
public final class IgnorablePutInMap<K, V> implements VoidTask<Map<K, V>> {
	
	/**
	 * @param key of pair.
	 * @param value of pair.
	 * @param validations It will not put the pair if it fails to pass all validations.
	 * @since 1.0.0
	 */
	@SafeVarargs
	public IgnorablePutInMap(final K key, final V value, final MapPutValidation<K, V>...validations) {
		this.key = key;
		this.value = value;
		this.validations = validations.clone();
	}
	
	@Override
	public void handle(final Map<K, V> map) {
		for (final MapPutValidation<K, V> validation : this.validations) {
			if (validation.valid(this.key, this.value, map) == false) {
				return;
			}
		}
		map.put(this.key, this.value);
	}
	
	private final K key;
	private final V value;
	private final MapPutValidation<K, V>[] validations;
}
