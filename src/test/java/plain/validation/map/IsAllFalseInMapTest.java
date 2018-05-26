package plain.validation.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.map.GiveableMap;
import plain.contract.task.ReturnTask;

class IsAllFalseInMapTest {

	// Dummy GiveableMap object to be validated.
	private GiveableMap<String, Boolean> giveableMap(final Map<String, Boolean> map) {
		return new GiveableMap<String, Boolean>() {
			@Override
			public Boolean value(final String key) {
				return map.get(key);
			}
			@Override
			public Boolean value(final ReturnTask<Boolean, Map<String, Boolean>> task) {
				return task.handle(map);
			}
			@Override
			public Set<String> keys() {
				return map.keySet();
			}
		};
	}
	
	@Test
	void testValidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put("Apple", false);
		map.put("Banana", false);
		map.put("Orange", false);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>();
		
		// Check if the map is valid.
		assertThat(isAllFalseInMap.valid(this.giveableMap(map)), new IsEqual<>(true));
	}
	
	@Test
	void testValidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put("Apple", false);
		map.put("Banana", true);
		map.put("Orange", false);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add("Apple");
		selectedKeys.add("Orange");
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>(selectedKeys);
		
		// Check if the map is valid.
		assertThat(isAllFalseInMap.valid(this.giveableMap(map)), new IsEqual<>(true));
	}
	
	@Test
	void testInvalidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put("Apple", false);
		map.put("Banana", false);
		map.put("Orange", true);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>();
		
		// Check if the map is invalid.
		assertThat(isAllFalseInMap.valid(this.giveableMap(map)), new IsEqual<>(false));
	}

	@Test
	void testInvalidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put("Apple", false);
		map.put("Banana", true);
		map.put("Orange", true);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add("Apple");
		selectedKeys.add("Orange");
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>(selectedKeys);
		
		// Check if the map is invalid.
		assertThat(isAllFalseInMap.valid(this.giveableMap(map)), new IsEqual<>(false));
	}
	
}
