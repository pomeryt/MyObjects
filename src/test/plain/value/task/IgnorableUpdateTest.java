package test.plain.value.task;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.value.EventValue;
import plain.value.contract.task.give.PlainGive;
import plain.value.contract.task.update.IgnorableUpdate;

class IgnorableUpdateTest {

	@Test
	void testIgnore() {
		// EventValue object.
		final EventValue<String> fruit = new EventValue<>();

		// Update the value to "Apple".
		fruit.update(new IgnorableUpdate<String>("Apple"));

		// Then, update the value again to null which should be ignored.
		fruit.update(new IgnorableUpdate<String>(null));

		// Check if the value is still "Apple" instead of null.
		assertThat(fruit.value(new PlainGive<>()), new IsEqual<>("Apple"));
	}

	@Test
	void testEventHandling() {
		// EventValue object.
		final EventValue<String> fruit = new EventValue<>();

		// List for storing fruits after each update.
		final List<String> fruits = new ArrayList<>();

		// Define event.
		fruit.addEvent(value -> {
			fruits.add(value);
		});

		// Update values including null values.
		fruit.update(new IgnorableUpdate<String>("Apple"));
		fruit.update(new IgnorableUpdate<String>(null));
		fruit.update(new IgnorableUpdate<String>("Banana"));
		fruit.update(new IgnorableUpdate<String>(null));
		fruit.update(new IgnorableUpdate<String>("Orange"));
		fruit.update(new IgnorableUpdate<String>(null));

		// Check the events.
		final List<String> expectedFruits = new ArrayList<>();
		expectedFruits.add("Apple");
		expectedFruits.add("Banana");
		expectedFruits.add("Orange");
		assertThat(fruits, new IsEqual<List<String>>(expectedFruits));
	}
}
