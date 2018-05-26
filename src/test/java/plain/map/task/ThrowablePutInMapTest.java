package plain.map.task;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapPutValidation;
import plain.map.task.ThrowablePutInMap;

class ThrowablePutInMapTest {

	@Test
	void testValidCaseWithSingleValidation() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String errorMessage = "Invalid.";
		final MapPutValidation<String, String> validation = (key, value, tempMap) -> true;
		final ThrowablePutInMap<String, String> throwableUpdateMap = new ThrowablePutInMap<String, String>("key", "value", errorMessage, validation);
		throwableUpdateMap.handle(map);
		
		// Check if the pair has been updated.
		assertThat(map.get("key"), new IsEqual<>("value"));
	}
	
	@Test
	void testValidCaseWithMultipleValidations() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String errorMessage = "Invalid.";
		final MapPutValidation<String, String> validation1 = (key, value, tempMap) -> true;
		final MapPutValidation<String, String> validation2 = (key, value, tempMap) -> true;
		final ThrowablePutInMap<String, String> throwableUpdateMap = new ThrowablePutInMap<String, String>("key", "value", errorMessage, validation1, validation2);
		throwableUpdateMap.handle(map);

		// Check if the pair has been updated.
		assertThat(map.get("key"), new IsEqual<>("value"));
	}

	@Test
	void testInValidCaseWithSingleValidation() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String errorMessage = "Invalid.";
		final MapPutValidation<String, String> validation = (key, value, tempMap) -> false;
		final ThrowablePutInMap<String, String> throwableUpdateMap = new ThrowablePutInMap<String, String>("key", "value", errorMessage, validation);
		
		// Check if it throws exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> throwableUpdateMap.handle(map));
		
		// Check if the error message is correct.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}
	
	@Test
	void testInValidCaseWithMultipleValidations() {
		// Prepare for the test.
		final Map<String, String> map = new HashMap<>();
		final String errorMessage = "Invalid.";
		final MapPutValidation<String, String> validation1 = (key, value, tempMap) -> true;
		final MapPutValidation<String, String> validation2 = (key, value, tempMap) -> false;
		final ThrowablePutInMap<String, String> throwableUpdateMap = new ThrowablePutInMap<String, String>("key", "value", errorMessage, validation1, validation2);

		// Check if it throws exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> throwableUpdateMap.handle(map));

		// Check if the error message is correct.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}
	
}
