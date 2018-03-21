package plain.number.count;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.number.count.PlainCount;

class PlainCountTest {

	@Test
	void testIncrementation() {
		// PlainCount object. Initial value is 0.
		final PlainCount count = new PlainCount();

		// Increment
		count.increment();

		// Check if the count is incremented correctly.
		assertThat(count.value(), new IsEqual<>(1));

		// Increment
		count.increment();

		// Check if the count is incremented correctly.
		assertThat(count.value(), new IsEqual<>(2));

		// Increment
		count.increment();

		// Check if the count is incremented correctly.
		assertThat(count.value(), new IsEqual<>(3));
	}

	@Test
	void testDefaultInitialValue() {
		assertThat(new PlainCount().value(), new IsEqual<>(0));
	}
	
	@Test
	void testInitialValue() {
		// Easy way to define the initial value.
		assertThat(new PlainCount(3).value(), new IsEqual<>(3));
		
		// More primitive way to define the initial value which is not recommended.
		assertThat(new PlainCount(new int[] {3}).value(), new IsEqual<>(3));
	}

}
