package plain.value;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.validation.ListValidation;
import plain.contract.validation.ValueValidation;
import plain.value.EventValue;
import plain.value.give.ThrowableGive;
import plain.value.update.ThrowableUpdate;

class EventValueTest {

	@Test
	void testDefaultBehavior() {
		// EventValue object.
		final EventValue<String> eventValue = new EventValue<>();
		
		// Update value as "Apple"
		eventValue.update("Apple");
		
		// Check the value.
		assertThat(eventValue.value(), new IsEqual<>("Apple"));
	}
	
	@Test
	void testEventHandling() {
		// EventValue object.
		final EventValue<String> eventValue = new EventValue<>();
		
		// List to check event handling.
		final List<String> actualList = new ArrayList<>();
		
		// Add an event to be handled.
		eventValue.addEvent(value -> actualList.add(value));
		
		// Update the value multiple times.
		eventValue.update("Apple");
		eventValue.update("Banana");
		eventValue.update("Orange");
		
		// Expected list after multiple updates.
		final List<String> expectedList = new ArrayList<>();
		expectedList.add("Apple");
		expectedList.add("Banana");
		expectedList.add("Orange");
		
		// Check if the event has been handled.
		assertThat(actualList, new IsEqual<>(expectedList));
	}
	
	@Test
	void testGiveValidation() {
		// ListValidation object that returns false always.
		final ListValidation<String> validation = list -> false;
		
		// Check if the value is invalid.
		final Exception exception = assertThrows(RuntimeException.class, () -> {
			new EventValue<String>().value(new ThrowableGive<>("Invalid.", validation));
		});
		
		// Check the error message.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}
	
	@Test
	void testUpdateValidation() {
		// ValueValidation object that returns false always.
		final ValueValidation<String> validation = value -> false;

		// Check if the value is invalid.
		final Exception exception = assertThrows(RuntimeException.class, () -> {
			new EventValue<String>().update(new ThrowableUpdate<String>(null, "Invalid.", validation));
		});

		// Check the error message.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}

	@Test
	void testEmptyCase() {
		// Check if it throws exception when the value does not exist.
		final Exception exception = assertThrows(RuntimeException.class, () -> {
			new EventValue<String>().value();
		});
		
		// Check the error message.
		assertThat(exception.getMessage(), new IsEqual<>("The value does not exist. Please update the value first."));
	}
}
