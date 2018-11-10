package plain.value.update;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.event.ParamEvent;
import plain.contract.validation.ValueValidation;

class IgnorableUpdateTest {

	@Test
	void testIgnore() {
		// ValueValidation object that returns false always.
		final ValueValidation<String> validation = value -> false;

		// IgnoreableUpdate object.
		final IgnorableUpdate<String> ignoreableUpdate = new IgnorableUpdate<String>(this.fruit1, validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();

		// List of ParamEvent objects to use IgnoreableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();
		
		// Try to update the list.
		ignoreableUpdate.handle(memory, events);

		// Check if the update has been ignored.
		assertThat(
			"The update should have been ignored because it was invalid.", 
			memory.size(), 
			new IsEqual<>(0)
		);
	}

	@Test
	void testValidCase() {
		// ValueValidation object that returns true always.
		final ValueValidation<String> validation = value -> true;

		// IgnoreableUpdate object.
		final IgnorableUpdate<String> ignoreableUpdate = new IgnorableUpdate<String>(this.fruit1, validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List of ParamEvent objects to use IgnoreableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();

		// Try to update the list.
		ignoreableUpdate.handle(memory, events);

		// Check if the update has been successful.
		assertThat(
			"The value should have been upadted because it was valid.", 
			memory.get(0), 
			new IsEqual<>(this.fruit1)
		);
	}
	
	@Test
	void testEventHandling() {
		// ValueValidation object that returns true always.
		final ValueValidation<String> validation = value -> true;

		// IgnoreableUpdate object.
		final IgnorableUpdate<String> ignoreableUpdate = new IgnorableUpdate<String>(this.fruit1, validation);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List to check the event handling.
		final List<String> actualList = new ArrayList<>();
		
		// ParamEvent object to handle update event.
		final ParamEvent<String> event = item -> {
			actualList.add(item);
		};

		// List of ParamEvent objects to use IgnoreableUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();
		events.add(event);

		// Try to update the list.
		ignoreableUpdate.handle(memory, events);

		// Expected list after update.
		final List<String> expectedList = new ArrayList<>();
		expectedList.add(this.fruit1);
		
		// Check the event handling.
		assertThat(
			"The event should have been handled.", 
			actualList, 
			new IsEqual<>(expectedList)
		);
	}
	
	private final String fruit1 = "Apple";
}
