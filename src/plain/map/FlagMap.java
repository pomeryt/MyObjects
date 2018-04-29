package plain.map;

import java.util.Map;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.run.RunnableViaTask;
import plain.contract.task.VoidTask;

/**
 * A map that contains boolean values. <br>
 * It is intended to be used to check conditions. <br>
 * However, you should not use this just to check conditions. <br>
 * Rather, you will pass the logic to this, and it will execute the logic for you when the condition is met. <br><br>
 * It is a decorator of another custom map. <br>
 * It is recommended to encapsulate FormalMap.
 * @author Rin
 * @version 2.0.0
 * @param <K> The type of key in map.
 * @param <M> An object type to be encapsulated.
 */
public final class FlagMap<K, M extends RegisterableMap<K, Boolean> & UpdateableMap<K, Boolean> & GiveableMap<K, Boolean>> implements RegisterableMap<K, Boolean>, UpdateableMap<K, Boolean>, RunnableViaTask<GiveableMap<K, Boolean>> {
	
	/**
	 * FormalMap is recommended for default origin.
	 * @param origin An object that implements RegisterableMap & UpdateableMap & GiveableMap.
	 * @since 2.0.0
	 */
	public FlagMap(final M origin) {
		this.origin = origin;
	}
	
	@Override
	public void update(final K key, final Boolean value) {
		this.origin.update(key, value);
	}

	@Override
	public void update(final VoidTask<Map<K, Boolean>> task) {
		this.origin.update(task);
	}

	@Override
	public void register(final K key, final Boolean value) {
		this.origin.register(key, value);
	}

	@Override
	public void register(final VoidTask<Map<K, Boolean>> task) {
		this.origin.register(task);
	}
	
	@Override
	public void run(final VoidTask<GiveableMap<K, Boolean>> task) {
		task.handle(this.origin);
	}
	
	private final M origin;

}
