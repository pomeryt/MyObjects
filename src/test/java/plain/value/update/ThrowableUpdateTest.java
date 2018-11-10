package plain.value.update;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import plain.value.EventValue;

class ThrowableUpdateTest {

	@Test
	void throwWhenInvalid() {
		final EventValue<Integer> number = new EventValue<>(0);
		Assertions.assertThrows(
			RuntimeException.class, 
			() -> {
				number.update(new ThrowableUpdate<>(1, "Invalid", value -> false));
			},
			"It should throw exception if it's invalid."
		);
	}
	
	@Test
	void doNotThrowIfValid() {
		final EventValue<Integer> number = new EventValue<>(0);
		number.update(new ThrowableUpdate<>(1, "Invalid", value -> true));
		MatcherAssert.assertThat(
			"The value should have been updated because it was valid.", 
			number.value(), 
			CoreMatchers.equalTo(1)
		);
	}

}
