package plain.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.task.ReturnTask;
import plain.contract.task.VoidTask;
import plain.map.task.ThrowableGiveFromMap;
import plain.map.task.ThrowablePutInMap;
import plain.validation.map.IsKeyNewToMap;
import plain.validation.map.IsKeyRegisteredInMap;

/**
 * It encapsulates a map with strategy pattern. <br>
 * The motivation of this class is to make your intention explicit when you put a pair into the map. <br>
 * For example, you should use register method to put a pair for the first time. <br>
 * And you should use update method to change the pair in map.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public final class FormalMap<K, V> implements RegisterableMap<K, V>, UpdateableMap<K, V>, GiveableMap<K, V> {
	
	/**
	 * Secondary constructor. <br>
	 * It instantiates HashMap.
	 * @since 2.0.0
	 */
	public FormalMap() {
		this(new HashMap<K, V>());
	}
	
	/**
	 * Primary constructor. <br>
	 * It encapsulates a Map.
	 * @param map to be encapsulated in this class.
	 * @since 1.0.0
	 */
	public FormalMap(final Map<K, V> map) {
		this.map = map;
	}
	
	@Override
	public V value(final K key) {
		return new ThrowableGiveFromMap<K, V>(
			key, "The key "+"'"+key+"'"+" has not been registered.", 
			new IsKeyRegisteredInMap<K, V>()
		).handle(this.map);
	}

	@Override
	public V value(final ReturnTask<V, Map<K, V>> task) {
		return task.handle(this.map);
	}

	@Override
	public Set<K> keys() {
		return this.map.keySet();
	}
	
	@Override
	public void update(final K key, final V value) {
		new ThrowablePutInMap<K, V>(
			key, value, 
			"The key "+"'"+key+"'"+" has not been registered.", 
			new IsKeyRegisteredInMap<K, V>()
		).handle(this.map);
	}

	@Override
	public void update(final VoidTask<Map<K, V>> task) {
		task.handle(this.map);
	}

	@Override
	public void register(final K key, final V value) {
		new ThrowablePutInMap<>(
			key, value, 
			"The key "+"'"+key+"'"+" has already been registered before.", 
			new IsKeyNewToMap<K, V>()
		).handle(this.map);
	}

	@Override
	public void register(final VoidTask<Map<K, V>> task) {
		task.handle(this.map);
	}
	
	private final Map<K, V> map;
}
