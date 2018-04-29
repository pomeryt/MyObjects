package plain.contract.map;

import java.util.Map;
import java.util.Set;

import plain.contract.task.ReturnTask;

/**
 * It allows you to obtain a value from the map. <br>
 * You may use the strategy pattern to obtain a value.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface GiveableMap<K, V> {
	/**
	 * @param key
	 * @return A value from the map.
	 * @since 1.0.0
	 */
	V value(K key);
	
	/**
	 * Apply the strategy pattern to obtain a value.
	 * @param task It has actual implementation to obtain a value.
	 * @return A value from the map.
	 * @since 2.0.0
	 */
	V value(ReturnTask<V, Map<K, V>> task);
	
	/**
	 * This method is for iterating the map.
	 * @return set of keys.
	 * @since 1.1.0
	 */
	Set<K> keys();
}
