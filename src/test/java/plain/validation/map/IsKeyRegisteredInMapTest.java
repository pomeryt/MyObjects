package plain.validation.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

class IsKeyRegisteredInMapTest {

	@Test
	void testValidCase() {
		// Prepare for the test.
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put("test", "Apple");
		final IsKeyRegisteredInMap<String, String> isKeyRegistered = new IsKeyRegisteredInMap<>();
		
		// Check if the key has been registered in map.
		assertThat(
			"The key should be valid if it exists in the map.",
			isKeyRegistered.valid("test", map), 
			new IsEqual<>(true)
		);
	}

	@Test
	void testInvalidCase() {
		// Prepare for the test.
		final Map<String, String> map = new ConcurrentHashMap<>();
		final IsKeyRegisteredInMap<String, String> isKeyRegistered = new IsKeyRegisteredInMap<>();
		
		// Check if the key has not been registered in map.
		assertThat(
			"The key should be invalid if it does not exist in the map.",
			isKeyRegistered.valid("test", map), 
			new IsEqual<>(false)
		);
	}
}
