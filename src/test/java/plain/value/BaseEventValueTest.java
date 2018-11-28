package plain.value;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class BaseEventValueTest {

	@Test
	public void exampleOfGettingValue() {
		final int initialNum = 3;
		final EventValue<Integer> number = new BaseEventValue<>(initialNum);
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(initialNum));
	}

	@Test
	public void exampleOfUpdatingValue() {
		final int initialNum = 3;
		final int updatedNum = 5;
		final EventValue<Integer> number = new BaseEventValue<>(initialNum);
		number.update(updatedNum);
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(updatedNum));
	}
	
	@Test
	public void exampleOfEventHandling() {
		final int initialNum = 3;
		final int updatedNum = 5;
		final StringBuilder actual = new StringBuilder(22);
		final EventValue<Integer> number = new BaseEventValue<>(initialNum);
		number.addEvent((oldValue, newValue) -> {
			actual.append("oldValue: " + oldValue + ", newValue: " + newValue);
		});
		number.update(updatedNum);
		MatcherAssert.assertThat(
			actual.toString(), 
			CoreMatchers.equalTo("oldValue: 3, newValue: 5")
		);
	}
	
}
