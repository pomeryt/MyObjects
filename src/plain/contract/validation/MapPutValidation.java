package plain.contract.validation;

import java.util.Map;

/**
 * It is responsible for validating a task that operates map.put(K key, V value).
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface MapPutValidation<K, V> {
	/**
	 * @param key
	 * @param value
	 * @param map
	 * @return true if it is valid map.put(K key, V value) operation.
	 * @since 1.0.0
	 */
	boolean valid(K key, V value, Map<K, V> map);
}
