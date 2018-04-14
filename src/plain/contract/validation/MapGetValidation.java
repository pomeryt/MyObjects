package plain.contract.validation;

import java.util.Map;

/**
 * It is responsible for validating a task that operates map.get(K key).
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface MapGetValidation<K, V> {
	/**
	 * @param key
	 * @param map
	 * @return true if it is valid map.get(K key) operation.
	 * @since 1.0.0
	 */
	boolean valid(K key, Map<K, V> map);
}
