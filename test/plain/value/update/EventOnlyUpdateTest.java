package plain.value.update;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.event.ParamEvent;
import plain.number.count.PlainCount;

class EventOnlyUpdateTest {

	@Test
	void test() {
		// Initial value in memory.
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		
		// It will count how many times the event has been handled.
		final PlainCount count = new PlainCount();
		
		// Add an event to increment the count.
		final List<ParamEvent<String>> events = new ArrayList<>();
		events.add(value -> count.increment());
		
		// Add an event to add current value to another ArrayList.
		final List<String> anotherMemory = new ArrayList<>();
		events.add(value -> anotherMemory.add(value));
		
		// Trigger the events twice.
		final EventOnlyUpdate<String> eventOnlyUpdate = new EventOnlyUpdate<>();
		eventOnlyUpdate.handle(memory, events);
		eventOnlyUpdate.handle(memory, events);
		
		// Check if the value is still the same.
		assertThat(memory.get(0), new IsEqual<>("Apple"));
		
		// Check if the events have been called twice.
		assertThat(2, new IsEqual<>(2));
		
		// Check if the events are handled based on the value already in the memory.
		assertThat(anotherMemory, new IsEqual<>(new ArrayList<>(Arrays.asList("Apple", "Apple"))));
	}

}
