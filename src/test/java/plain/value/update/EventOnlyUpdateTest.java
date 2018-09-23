package plain.value.update;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.number.count.PlainCount;
import plain.value.EventValue;

class EventOnlyUpdateTest {
	
	@Test
	void triggerEventsWithoutUpdateValue() {
		final EventValue<Integer> number = new EventValue<>(0);
		final PlainCount count = new PlainCount();
		number.addEvent(value -> count.increment());
		for (int x = 0; x < 3; x++) {
			number.update(new EventOnlyUpdate<>());
		}
		MatcherAssert.assertThat(number.value(), CoreMatchers.equalTo(0));
		MatcherAssert.assertThat(count.value(), CoreMatchers.equalTo(3));
	}

}
