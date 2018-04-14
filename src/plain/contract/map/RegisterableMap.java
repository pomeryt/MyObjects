package plain.contract.map;

import plain.contract.map.task.TaskToPutInMap;

/**
 * The intention is to prevent putting an item to the map with duplicated key. <br>
 * You may use the strategy pattern to register a key-value pair.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface RegisterableMap<K, V> {
	/**
	 * Default way to register a pair.
	 * @param key It should not have been registered before.
	 * @param value 
	 * @since 1.0.0
	 */
	void register(K key, V value);
	
	/**
	 * Apply the strategy pattern to register a pair.
	 * @param task It has actual implementation to register the key-value pair into the map.
	 * @since 1.0.0
	 */
	void register(TaskToPutInMap<K, V> task);
}
