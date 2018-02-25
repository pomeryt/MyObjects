package test.plain.value.task;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.value.EventValue;
import plain.value.contract.task.give.PlainGive;
import plain.value.contract.task.update.PlainUpdate;

class PlainTaskValueTest {

	@Test
	void testPlainBehaviors() {
		// EventValue object.
		final EventValue<String> fruit = new EventValue<>();

		// Update the value.
		fruit.update(new PlainUpdate<String>("Apple"));

		// Check if the value is updated correctly.
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

		// Update values.
		fruit.update(new PlainUpdate<String>("Apple"));
		fruit.update(new PlainUpdate<String>("Banana"));
		fruit.update(new PlainUpdate<String>("Orange"));

		// Check the events.
		final List<String> expectedFruits = new ArrayList<>();
		expectedFruits.add("Apple");
		expectedFruits.add("Banana");
		expectedFruits.add("Orange");
		assertThat(fruits, new IsEqual<List<String>>(expectedFruits));
	}

}
