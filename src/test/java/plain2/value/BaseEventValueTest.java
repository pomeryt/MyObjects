package plain2.value;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class BaseEventValueTest {

	@Test
	void exampleOfGettingValue() {
		final EventValue<Integer> number = new BaseEventValue<>(3);
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(3));
	}

	@Test
	void exampleOfUpdatingValue() {
		final EventValue<Integer> number = new BaseEventValue<>(3);
		number.update(5);
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(5));
	}
	
	@Test
	void exampleOfEventHandling() {
		final StringBuilder actual = new StringBuilder(22);
		final EventValue<Integer> number = new BaseEventValue<>(3);
		number.addEvent((oldValue, newValue) -> {
			actual.append("oldValue: " + oldValue + ", newValue: " + newValue);
		});
		number.update(5);
		MatcherAssert.assertThat(
			actual.toString(), 
			CoreMatchers.equalTo("oldValue: 3, newValue: 5")
		);
	}
	
}
