package plain.contract.map;

import java.util.Map;

import plain.contract.task.VoidTask;

/**
 * It updates the key-value pair in map. <br>
 * The intention is to make sure the key has been used before. <br>
 * In other words, it prevents putting a new pair by mistake such as typo on key. <br>
 * You may use the strategy pattern to update the pair.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface UpdateableMap<K, V> {
	/**
	 * @param key It should be registered before.
	 * @param value
	 * @since 1.0.0
	 */
	void update(K key, V value);
	
	/**
	 * Apply the strategy pattern to update the pair.
	 * @param task It has actual implementation of updating the pair in map.
	 * @since 2.0.0
	 */
	void update(VoidTask<Map<K, V>> task);
}
