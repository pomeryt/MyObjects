package test.plain.value;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.value.ThrowableValue;

class ThrowableValueTest {

	@Test
	void testGiveThrows() {
		assertThrows(RuntimeException.class,
				() -> new ThrowableValue<String>(this.giveErrorMessage, this.updateErrorMessage).value());
	}

	@Test
	void testUpdateThrows() {
		assertThrows(RuntimeException.class, () -> new ThrowableValue<String>(this.giveErrorMessage, this.updateErrorMessage).update(null));
	}

	@Test
	void testNormalBehavior() {
		// ThrowableValue object.
		final ThrowableValue<String> fruit = new ThrowableValue<>(this.giveErrorMessage, this.updateErrorMessage);

		// Update value.
		fruit.update("Apple");

		// Check the value.
		assertThat(fruit.value(), new IsEqual<String>("Apple"));
	}

	@Test
	void testEvent() {
		// ThrowableValue object.
		final ThrowableValue<String> fruit = new ThrowableValue<>(this.giveErrorMessage, this.updateErrorMessage);

		// List for storing fruits after each update.
		final List<String> fruits = new ArrayList<>();

		// Define event.
		fruit.addEvent(value -> {
			fruits.add(value);
		});

		// Update values.
		fruit.update("Apple");
		fruit.update("Banana");
		fruit.update("Orange");

		// Check the events.
		final List<String> expectedFruits = new ArrayList<>();
		expectedFruits.add("Apple");
		expectedFruits.add("Banana");
		expectedFruits.add("Orange");
		assertThat(fruits, new IsEqual<List<String>>(expectedFruits));
	}

	@Test
	void testMultipleEvents() {
		// ThrowableValue object.
		final ThrowableValue<String> fruit = new ThrowableValue<>(this.giveErrorMessage, this.updateErrorMessage);

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
		fruit.update("Apple");
		fruit.update("Banana");
		fruit.update("Orange");

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
