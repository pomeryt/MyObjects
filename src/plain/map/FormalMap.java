package plain.map;

import java.util.Map;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.map.task.TaskOfGiveableMap;
import plain.contract.map.task.TaskToPutInMap;
import plain.map.task.ThrowableGiveFromMap;
import plain.map.task.ThrowablePutInMap;
import plain.validation.map.IsKeyNewToMap;
import plain.validation.map.IsKeyRegisteredInMap;

/**
 * It encapsulates a map with strategy pattern. <br>
 * The motivation of this class is to make your intention explicit when you put a pair to the map. <br>
 * For example, you should use register method to put a pair for the first time. <br>
 * And you should use update method to change the pair in map.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class FormalMap<K, V> implements RegisterableMap<K, V>, UpdateableMap<K, V>, GiveableMap<K, V> {
	
	/**
	 * @param map to be encapsulated in this class.
	 * @since 1.0.0
	 */
	public FormalMap(final Map<K, V> map) {
		this.map = map;
	}
	
	@Override
	public V value(final K key) {
		return new ThrowableGiveFromMap<K, V>(key, "The key have not been registered.", new IsKeyRegisteredInMap<K, V>()).handle(this.map);
	}

	@Override
	public V value(final TaskOfGiveableMap<K, V> task) {
		return task.handle(this.map);
	}

	@Override
	public void update(final K key, final V value) {
		new ThrowablePutInMap<K, V>(key, value, "The key have not been registered.", new IsKeyRegisteredInMap<K, V>()).handle(this.map);
	}

	@Override
	public void update(final TaskToPutInMap<K, V> task) {
		task.handle(this.map);
	}

	@Override
	public void register(final K key, final V value) {
		new ThrowablePutInMap<>(key, value, "The key has already been registered before.", new IsKeyNewToMap<K, V>()).handle(this.map);
	}

	@Override
	public void register(final TaskToPutInMap<K, V> task) {
		task.handle(this.map);
	}
	
	private final Map<K, V> map;
}
