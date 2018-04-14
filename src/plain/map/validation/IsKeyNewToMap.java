package plain.map.validation;

import java.util.Map;

import plain.contract.validation.MapPutValidation;

/**
 * A task for the strategy pattern. <br>
 * It checks if the map does not contain the key. <br>
 * The purpose is to ensure the key has not been used before.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class IsKeyNewToMap<K, V> implements MapPutValidation<K, V> {

	@Override
	public boolean valid(final K key, final V value, final Map<K, V> map) {
		return !map.containsKey(key);
	}

}
