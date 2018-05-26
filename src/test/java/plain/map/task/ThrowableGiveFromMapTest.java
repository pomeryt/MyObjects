package plain.map.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapGetValidation;
import plain.map.task.ThrowableGiveFromMap;

class ThrowableGiveFromMapTest {

	@Test
	void testInvalidCaseWithSingleValidation() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String key = "test";
		final String errorMessage = "Invalid.";
		final MapGetValidation<String, String> validation = (tempKey, tempMap) -> false;
		final ThrowableGiveFromMap<String, String> throwableGiveFromMap = new ThrowableGiveFromMap<String, String>(key, errorMessage, validation);
		
		// Check if it throws exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> throwableGiveFromMap.handle(map));
		
		// Check if the error message is correct.
		assertThat(exception.getMessage(), new IsEqual<>(errorMessage));
	}
	
	@Test
	void testValidCaseWithSingleValidation() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String key = "test";
		final String errorMessage = "Invalid.";
		final MapGetValidation<String, String> validation = (tempKey, tempMap) -> true;
		final ThrowableGiveFromMap<String, String> throwableGiveFromMap = new ThrowableGiveFromMap<String, String>(key, errorMessage, validation);
		map.put(key, "Apple");
		
		// Check if it gives "Apple".
		assertThat(throwableGiveFromMap.handle(map), new IsEqual<>("Apple"));
	}
	
	@Test
	void testInvalidCaseWithMultipleValidations() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String key = "test";
		final String errorMessage = "Invalid.";
		final MapGetValidation<String, String> validation1 = (tempKey, tempMap) -> true;
		final MapGetValidation<String, String> validation2 = (tempKey, tempMap) -> false;
		final ThrowableGiveFromMap<String, String> throwableGiveFromMap = new ThrowableGiveFromMap<String, String>(key, errorMessage, validation1, validation2);
		
		// Check if it throws exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> throwableGiveFromMap.handle(map));

		// Check if the error message is correct.
		assertThat(exception.getMessage(), new IsEqual<>(errorMessage));
	}
	
	@Test
	void testValidCaseWithMultipleValidations() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String key = "test";
		final String errorMessage = "Invalid.";
		final MapGetValidation<String, String> validation1 = (tempKey, tempMap) -> true;
		final MapGetValidation<String, String> validation2 = (tempKey, tempMap) -> true;
		final ThrowableGiveFromMap<String, String> throwableGiveFromMap = new ThrowableGiveFromMap<String, String>(key, errorMessage, validation1, validation2);
		map.put(key, "Apple");

		// Check if it gives "Apple".
		assertThat(throwableGiveFromMap.handle(map), new IsEqual<>("Apple"));
	}
}
