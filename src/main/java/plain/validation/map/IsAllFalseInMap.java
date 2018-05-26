package plain.validation.map;

import java.util.ArrayList;
import java.util.List;

import plain.contract.map.GiveableMap;
import plain.contract.validation.GiveableMapValidation;

/**
 * It checks if the selected values are all false. <br>
 * You can select values by passing a list that contains corresponding keys. <br>
 * If you don't select anything (providing empty list), then it will check all values in the map.
 * @author Rin
 * @version 1.0.0
 * @param <K> The type of key.
 */
public final class IsAllFalseInMap<K> implements GiveableMapValidation<K, Boolean> {

	/**
	 * It will create an empty list and pass it to primary constructor. <br>
	 * You will use this secondary constructor to check all values in the map.
	 * @since 1.0.0
	 */
	public IsAllFalseInMap() {
		this(new ArrayList<K>());
	}
	
	/**
	 * Primary constructor. <br>
	 * Use this constructor to specify the selected values to be checked.
	 * @param selectedKeys A list that contains corresponding keys.
	 * @since 1.0.0
	 */
	public IsAllFalseInMap(final List<K> selectedKeys) {
		this.selectedKeys = selectedKeys;
	}
	
	@Override
	public boolean valid(final GiveableMap<K, Boolean> giveableMap) {
		// Check all values in map when the keys are not selected at all.
		if (this.selectedKeys.isEmpty()) {
			for (K key : giveableMap.keys()) {
				if (giveableMap.value(key)) {
					return false;
				}
			}
			return true;
		}
		
		// Check only the values for selected keys in map.
		for (K key : this.selectedKeys) {
			if (giveableMap.value(key)) {
				return false;
			}
		}
		return true;
	}
	
	private final List<K> selectedKeys;
}
