package plain.contract.map;

import plain.contract.map.task.TaskOfGiveableMap;

/**
 * It allows you to obtain a value from the map. <br>
 * You may use the strategy pattern to obtain a value.
 * @author Rin
 * @version 1.0.0
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
	 * @since 1.0.0
	 */
	V value(TaskOfGiveableMap<K, V> task);
}
