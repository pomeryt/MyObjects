package plain.map;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.map.task.ConditionalRunForMap;
import plain.number.count.PlainCount;
import plain.validation.map.IsAllTrueInMap;

class FlagMapTest {

	@Test
	void testNormalBehavior() {
		// FlagMap object to be tested.
		final FlagMap<String> flagMap = new FlagMap<>(new HashMap<>());
		flagMap.register("Apple", true);
		flagMap.register("Banana", false);
		flagMap.register("Orange", true);
		
		// Counter to check whether the runnable has been executed or not.
		final PlainCount count = new PlainCount();
		
		// Keys to be checked in condition.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add("Apple");
		selectedKeys.add("Orange");
		
		// Try to run the runnable.
		flagMap.run(new ConditionalRunForMap<>(() -> count.increment(), new IsAllTrueInMap<>(selectedKeys)));
		
		// Check if the logic has been executed.
		assertThat(count.value(), new IsEqual<>(1));
	}
	
	@Test
	void testIgnoredBehavior() {
		// FlagMap object to be tested.
		final FlagMap<String> flagMap = new FlagMap<>(new HashMap<>());
		flagMap.register("Apple", true);
		flagMap.register("Banana", false);
		flagMap.register("Orange", true);
		
		// Counter to check whether the runnable has been executed or not.
		final PlainCount count = new PlainCount();
		
		// Keys to be checked in condition.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add("Apple");
		selectedKeys.add("Banana");
		
		// Try to run the runnable.
		flagMap.run(new ConditionalRunForMap<>(() -> count.increment(), new IsAllTrueInMap<>(selectedKeys)));
		
		// Check if the logic has not been executed.
		assertThat(count.value(), new IsEqual<>(0));
	}

}
