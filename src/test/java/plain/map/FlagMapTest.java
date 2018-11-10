package plain.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
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
		final FlagMap<String> flagMap = new FlagMap<>(new FormalMap<>());
		flagMap.register(this.fruit1, true);
		flagMap.register(this.fruit2, false);
		flagMap.register(this.fruit3, true);
		
		// Counter to check whether the runnable has been executed or not.
		final PlainCount count = new PlainCount();
		
		// Keys to be checked in condition.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit3);
		
		// Try to run the runnable.
		flagMap.run(new ConditionalRunForMap<>(() -> count.increment(), new IsAllTrueInMap<>(selectedKeys)));
		
		// Check if the logic has been executed.
		assertThat("The logic should've been executed if the validation was met.", count.value(), new IsEqual<>(1));
	}
	
	@Test
	void testIgnoredBehavior() {
		// FlagMap object to be tested.
		final FlagMap<String> flagMap = new FlagMap<>(new FormalMap<>());
		flagMap.register(this.fruit1, true);
		flagMap.register(this.fruit2, false);
		flagMap.register(this.fruit3, true);
		
		// Counter to check whether the runnable has been executed or not.
		final PlainCount count = new PlainCount();
		
		// Keys to be checked in condition.
		final List<String> selectedKeys = new ArrayList<>();
		selectedKeys.add(this.fruit1);
		selectedKeys.add(this.fruit2);
		
		// Try to run the runnable.
		flagMap.run(new ConditionalRunForMap<>(() -> count.increment(), new IsAllTrueInMap<>(selectedKeys)));
		
		// Check if the logic has not been executed.
		assertThat(
			"The logic should've not been executed if the validation is not met.", 
			count.value(), 
			new IsEqual<>(0)
		);
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
	private final String fruit3 = "Orange";

}
