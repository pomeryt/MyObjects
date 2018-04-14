package plain.contract.map.task;

import java.util.Map;

/**
 * A task for strategy pattern. <br>
 * It is responsible for obtaining a value from the map.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface TaskOfGiveableMap<K, V> {
	/**
	 * @param map
	 * @return A value from the map.
	 * @since 1.0.0
	 */
	V handle(Map<K, V> map);
}
