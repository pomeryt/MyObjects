package plain.validation.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import plain.contract.map.GiveableMap;
import plain.contract.task.ReturnTask;

@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
class IsAllTrueInMapTest {

	@Test
	void testValidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, true);
		map.put(this.fruit2, true);
		map.put(this.fruit3, true);
		
		// Dummy GiveableMap object which uses above map.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
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
		
		// The object to be tested.
		final IsAllTrueInMap<String> isAllTrueInMap = new IsAllTrueInMap<>();
		
		// Check if the map is valid.
		assertThat(isAllTrueInMap.valid(giveableMap), new IsEqual<>(true));
	}
	
	@Test
	void testValidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, true);
		map.put(this.fruit2, false);
		map.put(this.fruit3, true);
		
		// Dummy GiveableMap object which uses above map.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
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
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsAllTrueInMap<String> isAllTrueInMap = new IsAllTrueInMap<>(selectedKeys);
		
		// Check if the map is valid.
		assertThat(isAllTrueInMap.valid(giveableMap), new IsEqual<>(true));
	}
	
	@Test
	void testInvalidCaseWithoutSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, true);
		map.put(this.fruit2, true);
		map.put(this.fruit3, false);
		
		// Dummy GiveableMap object which uses above map.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
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
		
		// The object to be tested.
		final IsAllTrueInMap<String> isAllTrueInMap = new IsAllTrueInMap<>();
		
		// Check if the map is invalid.
		assertThat(isAllTrueInMap.valid(giveableMap), new IsEqual<>(false));
	}

	@Test
	void testInvalidCaseWithSelection() {
		// A map to be validated.
		final Map<String, Boolean> map = new HashMap<>();
		map.put(this.fruit1, true);
		map.put(this.fruit2, true);
		map.put(this.fruit3, false);
		
		// Dummy GiveableMap object which uses above map.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
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
		
		// Select keys to be tested.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// The object to be tested.
		final IsAllTrueInMap<String> isAllTrueInMap = new IsAllTrueInMap<>(selectedKeys);
		
		// Check if the map is invalid.
		assertThat(isAllTrueInMap.valid(giveableMap), new IsEqual<>(false));
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
	private final String fruit3 = "Orange";
}
