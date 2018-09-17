package plain.map;

import plain.contract.map.GiveableMap;
import plain.contract.map.RegisterableMap;
import plain.contract.map.UpdateableMap;

/**
 * A marker interface that helps you compose your objects.
 * A base class is {@link FormalMap}.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface StrictMap<K, V> extends RegisterableMap<K, V>, UpdateableMap<K, V>, GiveableMap<K, V> {}
