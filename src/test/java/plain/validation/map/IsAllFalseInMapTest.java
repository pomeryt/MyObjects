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
		map.put(this.fruit1, false);
		map.put(this.fruit2, false);
		map.put(this.fruit3, false);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>();
		
		// Check if the map is valid.
		assertThat(
			"The map should be valid if all values are false.",
			isAllFalseInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(true)
		);
	}
	
	@Test
	void testValidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, true);
		map.put(this.fruit3, false);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>(selectedKeys);
		
		// Check if the map is valid.
		assertThat(
			"The map should be valid if all selected values are false.",
			isAllFalseInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(true)
		);
	}
	
	@Test
	void testInvalidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, false);
		map.put(this.fruit3, true);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>();
		
		// Check if the map is invalid.
		assertThat(
			"The map should be invalid if it has true.",
			isAllFalseInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(false)
		);
	}

	@Test
	void testInvalidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, true);
		map.put(this.fruit3, true);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsAllFalseInMap<String> isAllFalseInMap = new IsAllFalseInMap<>(selectedKeys);
		
		// Check if the map is invalid.
		assertThat(
			"The map should be invalid if there is a true in selected values.",
			isAllFalseInMap.valid(this.giveableMap(map)),
			new IsEqual<>(false)
		);
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
	private final String fruit3 = "Orange";
	
}
