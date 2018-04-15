package plain.validation.map;

import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.validation.map.IsKeyRegisteredInMap;

class IsKeyRegisteredInMapTest {

	@Test
	void testValidCase() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		map.put("test", "Apple");
		final IsKeyRegisteredInMap<String, String> isKeyRegisteredInMap = new IsKeyRegisteredInMap<>();
		
		// Check if the key has been registered in map.
		assertThat(isKeyRegisteredInMap.valid("test", map), new IsEqual<>(true));
	}

	@Test
	void testInvalidCase() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final IsKeyRegisteredInMap<String, String> isKeyRegisteredInMap = new IsKeyRegisteredInMap<>();
		
		// Check if the key has not been registered in map.
		assertThat(isKeyRegisteredInMap.valid("test", map), new IsEqual<>(false));
	}
}
