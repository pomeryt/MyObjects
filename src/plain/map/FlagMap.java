package plain.map;

import java.util.Map;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.map.task.TaskToPutInMap;
import plain.contract.run.RunnableViaTask;
import plain.contract.run.task.TaskOfRunnable;
import plain.value.CachedValue;

/**
 * A map that contains boolean values. <br>
 * It is intended to be used to check conditions. <br>
 * However, you should not use this just to check conditions. <br>
 * Rather, you will pass the logic to this, and it will execute the logic for you when the condition is met.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key in map.
 */
public final class FlagMap<K> implements RegisterableMap<K, Boolean>, UpdateableMap<K, Boolean>, RunnableViaTask<GiveableMap<K, Boolean>> {
	
	/**
	 * It encapsulates a map to instantiate FormalMap.
	 * @param map
	 * @since 1.0.0
	 */
	public FlagMap(final Map<K, Boolean> map) {
		this(new CachedValue<>(() -> {
			return new FormalMap<>(map);
		}));
	}
	
	/**
	 * Primary constructor.
	 * @param cachedFormalMap
	 * @since 1.0.0
	 */
	public FlagMap(final CachedValue<FormalMap<K, Boolean>> cachedFormalMap) {
		this.cachedFormalMap = cachedFormalMap;
	}
	
	@Override
	public void update(final K key, final Boolean value) {
		this.cachedFormalMap.value().update(key, value);
	}

	@Override
	public void update(final TaskToPutInMap<K, Boolean> task) {
		this.cachedFormalMap.value().update(task);
	}

	@Override
	public void register(final K key, final Boolean value) {
		this.cachedFormalMap.value().register(key, value);
	}

	@Override
	public void register(final TaskToPutInMap<K, Boolean> task) {
		this.cachedFormalMap.value().register(task);
	}
	
	@Override
	public void run(final TaskOfRunnable<GiveableMap<K, Boolean>> task) {
		task.handle(this.cachedFormalMap.value());
	}
	
	private final CachedValue<FormalMap<K, Boolean>> cachedFormalMap;

}
