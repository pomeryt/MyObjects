package test.plain.value.update;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.event.ParamEvent;
import plain.contract.validation.ValueValidation;
import plain.value.update.ThrowableUpdate;

class ThrowableUpdateTest {

	@Test
	void testValidCase() {
		// ValueValidation object that returns true always.
		final ValueValidation<String> validation = value -> true;

		// ThrowableUpdate object.
		final ThrowableUpdate<String> throwableUpdate = new ThrowableUpdate<String>("Apple", "Invalid.", validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List of ParamEvent objects to use ThrowableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();

		// Try to update the list.
		throwableUpdate.handle(memory, events);

		// Check if the update has been successful.
		assertThat(memory.get(0), new IsEqual<>("Apple"));
	}
	
	@Test
	void testInvalidCase() {
		// ValueValidation object that returns false always.
		final ValueValidation<String> validation = value -> false;

		// ThrowableUpdate object.
		final ThrowableUpdate<String> throwableUpdate = new ThrowableUpdate<String>("Apple", "Invalid.", validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();

		// List of ParamEvent objects to use ThrowableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();

		// Check if the ThrowableUpdate object throws RuntimeException.
		final Throwable exception = assertThrows(RuntimeException.class, () -> throwableUpdate.handle(memory, events));

		// Check the message from the exception.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}
	
	@Test
	void testEventHandling() {
		// ValueValidation object that returns true always.
		final ValueValidation<String> validation = value -> true;

		// ThrowableUpdate object.
		final ThrowableUpdate<String> throwableUpdate = new ThrowableUpdate<String>("Apple", "Invalid.", validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List to check the event handling.
		final List<String> actualList = new ArrayList<>();
		
		// ParamEvent object to handle update event.
		final ParamEvent<String> event = item -> {
			actualList.add(item);
		};

		// List of ParamEvent objects to use ThrowableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();
		events.add(event);

		// Try to update the list.
		throwableUpdate.handle(memory, events);

		// Expected list after update.
		final List<String> expectedList = new ArrayList<>();
		expectedList.add("Apple");
		
		// Check the event handling.
		assertThat(actualList, new IsEqual<>(expectedList));
	}

}
