package plain.value.update;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.value.EventValue;

class ValueOnlyUpdateTest {

	@Test
	void updateValueWithoutTriggersEvents() {
		final EventValue<Integer> number = new EventValue<>();
		number.addEvent(value -> {
			throw new UnsupportedOperationException("You are not supposed to call this method.");
		});
		number.update(new ValueOnlyUpdate<>(0));
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(0));
	}

}
