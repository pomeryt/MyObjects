package plain.map;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

class FormalMapTest {

	@Test
	void testNormalBehavior() {
		// FormalMap object.
		final FormalMap<String, String> formalMap = new FormalMap<>(new HashMap<>());
		
		// Register a pair to formal map.
		formalMap.register("One", "Apple");
		
		// Check if the pair has been registered.
		assertThat(formalMap.value("One"), new IsEqual<>("Apple"));
		
		// Update the pair.
		formalMap.update("One", "Orange");
		
		// Check if the pair has been updated.
		assertThat(formalMap.value("One"), new IsEqual<>("Orange"));
	}
	
	@Test
	void testInvalidCase() {
		// FormalMap object.
		final FormalMap<String, String> formalMap = new FormalMap<>(new HashMap<>());
		
		// Check if it throws exception when you ask a value for unregistered key.
		assertThrows(RuntimeException.class, () -> formalMap.value("One"));
		
		// Check if it throws exception when you try to update a pair in which the key has not been registered before.
		assertThrows(RuntimeException.class, () -> formalMap.update("One", "Apple"));
		
		// Register a pair.
		formalMap.register("One", "Apple");
		
		// Check if it throws exception when you try to register a pair in which the key has already been registered before.
		assertThrows(RuntimeException.class, () -> formalMap.register("One", "Apple"));
	}

}
