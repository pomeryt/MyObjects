package test.plain.value;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.value.EventValue;
import plain.value.contract.task.give.ThrowableGive;
import plain.value.contract.task.update.ThrowableUpdate;

class EventValueTest {

	@Test
	void testGiveThrows() {
		assertThrows(RuntimeException.class,
				() -> new EventValue<String>().value(new ThrowableGive<>(this.giveErrorMessage)));
	}

	@Test
	void testUpdateThrows() {
		assertThrows(RuntimeException.class, () -> new EventValue<String>()
				.update(new ThrowableUpdate<String>(null, this.updateErrorMessage)));
	}

	@Test
	void testNormalBehavior() {
		// EventVlaue object.
		final EventValue<String> fruit = new EventValue<>();

		// Update value.
		fruit.update(new ThrowableUpdate<String>("Apple", this.updateErrorMessage));

		// Check the value.
		assertThat(fruit.value(new ThrowableGive<>(this.giveErrorMessage)), new IsEqual<String>("Apple"));
	}

	@Test
	void testEvent() {
		// EventVlaue object.
		final EventValue<String> fruit = new EventValue<>();

		// List for storing fruits after each update.
		final List<String> fruits = new ArrayList<>();

		// Define event.
		fruit.addEvent(value -> {
			fruits.add(value);
		});

		// Update values.
		fruit.update(new ThrowableUpdate<String>("Apple", this.updateErrorMessage));
		fruit.update(new ThrowableUpdate<String>("Banana",this.updateErrorMessage));
		fruit.update(new ThrowableUpdate<String>("Orange", this.updateErrorMessage));

		// Check the events.
		final List<String> expectedFruits = new ArrayList<>();
		expectedFruits.add("Apple");
		expectedFruits.add("Banana");
		expectedFruits.add("Orange");
		assertThat(fruits, new IsEqual<List<String>>(expectedFruits));
	}

	@Test
	void testMultipleEvents() {
		// EventVlaue object.
		final EventValue<String> fruit = new EventValue<>();

		// List for storing fruits after each update.
		final List<String> fruits = new ArrayList<>();

		// Add fruit to the list when the value is updated.
		fruit.addEvent(value -> {
			fruits.add(value);
		});
		
		// List for storing fruits with all lower case.
		final List<String> lowerCasedFruits = new ArrayList<>();
		
		// Add fruit to the list with lower case.
		fruit.addEvent(value -> {
			lowerCasedFruits.add(value.toLowerCase());
		});

		// Update values.
		fruit.update(new ThrowableUpdate<String>("Apple", this.updateErrorMessage));
		fruit.update(new ThrowableUpdate<String>("Banana", this.updateErrorMessage));
		fruit.update(new ThrowableUpdate<String>("Orange", this.updateErrorMessage));

		// Check the normal fruits.
		final List<String> expectedFruits = new ArrayList<>();
		expectedFruits.add("Apple");
		expectedFruits.add("Banana");
		expectedFruits.add("Orange");
		assertThat(fruits, new IsEqual<List<String>>(expectedFruits));
		
		// Check the lowerCasedFruits.
		final List<String> expectedLowerCasedFruits = new ArrayList<>();
		expectedLowerCasedFruits.add("apple");
		expectedLowerCasedFruits.add("banana");
		expectedLowerCasedFruits.add("orange");
		assertThat(lowerCasedFruits, new IsEqual<List<String>>(expectedLowerCasedFruits));
	}
	
	private final String giveErrorMessage = "The value does not exist. Please update value before.";
	private final String updateErrorMessage = "Do not update with null value.";
}
