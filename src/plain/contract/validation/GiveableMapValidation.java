package plain.contract.validation;

import plain.contract.map.GiveableMap;

/**
 * It validates the GiveableMap.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 * @param <V> The type of value.
 */
public interface GiveableMapValidation<K, V> {
	/**
	 * @param giveableMap
	 * @return true if the map is valid.
	 * @since 1.0.0
	 */
	boolean valid(GiveableMap<K, V> giveableMap);
}
