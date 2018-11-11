package plain.validation.map;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

class IsKeyNewToMapTest {

	@Test
	void testValidCase() {
		// Prepare for the test.
		final Map<String, String> map = new ConcurrentHashMap<>();
		final IsKeyNewToMap<String, String> newKeyValidation = new IsKeyNewToMap<>();
		
		// Check if the key has not been used before.
		assertThat(
			"The key should be valid if it has not been used before.",
			newKeyValidation.valid("key", "value", map), 
			new IsEqual<>(true)
		);
	}
	
	@Test
	void testInvalidCase() {
		// Prepare for the test.
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put("key", "value");
		final IsKeyNewToMap<String, String> newKeyValidation = new IsKeyNewToMap<>();
		
		// Check if the key is invalid.
		assertThat(
			"The key should be invalid if it has been used before.",
			newKeyValidation.valid("key", "value", map), 
			new IsEqual<>(false)
		);
	}

}
