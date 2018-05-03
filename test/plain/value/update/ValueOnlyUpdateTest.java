package plain.value.update;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.event.ParamEvent;
import plain.contract.validation.ValueValidation;

class ValueOnlyUpdateTest {

	@Test
	void testValidCaseWithDefaultValidation() {
		// Initial value in memory.
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		
		// Change the value in memory.
		final ValueOnlyUpdate<String> valueOnlyUpdate = new ValueOnlyUpdate<String>("Banana");
		valueOnlyUpdate.handle(memory, new ArrayList<ParamEvent<String>>());
		
		// Check if the value has been changed correctly.
		assertThat(memory.get(0), new IsEqual<>("Banana"));
	}
	
	@Test
	void testInvalidCaseWithDefaultValidation() {
		// Try to change the value to null.
		final ValueOnlyUpdate<String> valueOnlyUpdate = new ValueOnlyUpdate<String>(null);
		
		// Check if it throws an exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> valueOnlyUpdate.handle(new ArrayList<String>(), new ArrayList<ParamEvent<String>>()));
		
		// Check the error message.
		assertThat(exception.getMessage(), new IsEqual<>("You cannot change the value to null."));
	}
	
	@Test
	void testValidCaseWitCustomtValidation() {
		// Initial value in memory.
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		
		// Custom validation that always say the value is valid.
		final ValueValidation<String> validation = value -> true;
		
		// Change the value in memory.
		final ValueOnlyUpdate<String> valueOnlyUpdate = new ValueOnlyUpdate<String>("Banana", "Invalid", validation);
		valueOnlyUpdate.handle(memory, new ArrayList<ParamEvent<String>>());
		
		// Check if the value has been changed correctly.
		assertThat(memory.get(0), new IsEqual<>("Banana"));
	}
	
	@Test
	void testInvalidCaseWitCustomtValidation() {
		// Custom validation that always say the value is invalid.
		final ValueValidation<String> validation = value -> false;
		
		// Apply custom validation.
		final ValueOnlyUpdate<String> valueOnlyUpdate = new ValueOnlyUpdate<String>("Banana", "Invalid.", validation);

		// Check if it throws an exception.
		final Exception exception = assertThrows(RuntimeException.class, () -> valueOnlyUpdate.handle(new ArrayList<String>(), new ArrayList<ParamEvent<String>>()));

		// Check the error message.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}

}
