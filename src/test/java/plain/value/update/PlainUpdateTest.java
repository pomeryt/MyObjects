package plain.value.update;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.event.ParamEvent;

class PlainUpdateTest {

	@Test
	void testUpdate() {

		// PlainUpdate object.
		final PlainUpdate<String> plainUpdate = new PlainUpdate<String>(this.fruit1);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List of ParamEvent objects to use PlainUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();

		// Try to update the list.
		plainUpdate.handle(memory, events);

		// Check if the update has been successful.
		assertThat(
			"The value should been updated.", 
			memory.get(0), 
			new IsEqual<>(this.fruit1)
		);
	}
	
	@Test
	void testEventHandling() {

		// PlainUpdate object.
		final PlainUpdate<String> plainUpdate = new PlainUpdate<String>(this.fruit1);

		// List to be updated.
		final List<String> memory = new ArrayList<>();
		
		// List to check the event handling.
		final List<String> actualList = new ArrayList<>();
		
		// ParamEvent object to handle update event.
		final ParamEvent<String> event = item -> {
			actualList.add(item);
		};

		// List of ParamEvent objects to use PlainUpdate object.
		final List<ParamEvent<String>> events = new ArrayList<>();
		events.add(event);

		// Try to update the list.
		plainUpdate.handle(memory, events);

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
