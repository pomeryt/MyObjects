package plain.map;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;
import plain.contract.run.RunnableViaTask;

/**
 * A marker interface that helps you compose your objects.
 * A base class is {@link FlagMap}.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface RunnableMap<K, V> extends RegisterableMap<K, V>, UpdateableMap<K, V>, RunnableViaTask<GiveableMap<K, V>> {}
