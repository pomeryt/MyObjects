package plain.contract.map.task;

import java.util.Map;

/**
 * A task for strategy pattern. <br>
 * It is responsible for putting a key-value pair to the map.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface TaskToPutInMap<K, V> {
	/**
	 * It implements how to put a pair into the map.
	 * @param map
	 * @since 1.0.0
	 */
	void handle(Map<K, V> map);
}
