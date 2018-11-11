package plain.validation.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.map.GiveableMap;
import plain.contract.task.ReturnTask;

class IsThereTrueInMapTest {
	
	@Test
	void testValidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new ConcurrentHashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, true);
		map.put(this.fruit3, false);
		
		// The object to be tested.
		final IsThereTrueInMap<String> isThereTrueInMap = new IsThereTrueInMap<>();
		
		// Check if the map is valid.
		assertThat(
			"The map should be valid if there is a true.",
			isThereTrueInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(true)
		);
	}
		
	@Test
	void testValidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new ConcurrentHashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, false);
		map.put(this.fruit3, true);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsThereTrueInMap<String> isThereTrueInMap = new IsThereTrueInMap<>(selectedKeys);
		
		// Check if the map is valid.
		assertThat(
			"The map should be valid if there is a true in the selected values.",
			isThereTrueInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(true)
		);
	}
		
	@Test
	void testInvalidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new ConcurrentHashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, false);
		map.put(this.fruit3, false);
		
		// The object to be tested.
		final IsThereTrueInMap<String> isThereTrueInMap = new IsThereTrueInMap<>();
		
		// Check if the map is invalid.
		assertThat(
			"The map should be invalid if it does not have true.",
			isThereTrueInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(false)
		);
	}

	@Test
	void testInvalidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new ConcurrentHashMap<>();
		map.put(this.fruit1, false);
		map.put(this.fruit2, true);
		map.put(this.fruit3, false);
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsThereTrueInMap<String> isThereTrueInMap = new IsThereTrueInMap<>(selectedKeys);
		
		// Check if the map is invalid.
		assertThat(
			"The map should be invalid if there is no true in the selected values.",
			isThereTrueInMap.valid(this.giveableMap(map)), 
			new IsEqual<>(false)
		);
	}
	
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
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
	private final String fruit3 = "Orange";
	
}
