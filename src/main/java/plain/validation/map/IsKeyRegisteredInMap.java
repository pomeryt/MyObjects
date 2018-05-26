package plain.validation.map;

import java.util.Map;

import plain.contract.validation.MapGetValidation;
import plain.contract.validation.MapPutValidation;

/**
 * It checks if the map contains the key.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class IsKeyRegisteredInMap<K, V> implements MapGetValidation<K, V>, MapPutValidation<K, V> {

	@Override
	public boolean valid(final K key, final Map<K, V> map) {
		return map.containsKey(key);
	}

	@Override
	public boolean valid(final K key, final V value, final Map<K, V> map) {
		return map.containsKey(key);
	}

}