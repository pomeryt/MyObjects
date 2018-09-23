package plain.validation.map;

import java.util.ArrayList;
import java.util.List;

import plain.contract.map.GiveableMap;
import plain.contract.validation.GiveableMapValidation;

/**
 * It checks if true exists in selected keys. <br>
 * You can select values by passing a list that contains corresponding keys. <br>
 * If you don't select anything (providing empty list), then it will check all values in the map.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 */
public final class IsThereTrueInMap<K> implements GiveableMapValidation<K, Boolean> {

	/**
	 * It will create an empty list and pass it to primary constructor. <br>
	 * You will use this secondary constructor to check all values in the map.
	 * @since 1.0.0
	 */
	public IsThereTrueInMap() {
		this(new ArrayList<K>());
	}
	
	/**
	 * Primary constructor. <br>
	 * Use this constructor to specify the selected values to be checked.
	 * @param selectedKeys A list that contains corresponding keys.
	 * @since 1.0.0
	 */
	public IsThereTrueInMap(final List<K> selectedKeys) {
		this.selectedKeys = selectedKeys;
	}
	
	@Override
	public boolean valid(final GiveableMap<K, Boolean> giveableMap) {
		// Check all values in map when the keys are not selected at all.
		if (this.selectedKeys.isEmpty()) {
			for (final K key : giveableMap.keys()) {
				if (giveableMap.value(key)) {
					return true;
				}
			}
			return false;
		}
		
		// Check only the values for selected keys in map.
		for (final K key : this.selectedKeys) {
			if (giveableMap.value(key)) {
				return true;
			}
		}
		return false;
	}
	
	private final List<K> selectedKeys;
}
