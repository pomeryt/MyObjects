package plain.value;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class EventValueTest {

	@Test
	void defaultValueViaConstructor() {
		MatcherAssert.assertThat(
			"The value injected by constructor should be returned.",
			new EventValue<Integer>(0).value(), 
			CoreMatchers.equalTo(0)
		);
	}
	
	@Test
	void valueViaTask() {
		MatcherAssert.assertThat(
			"The value injected by constructor should be returned via task.",
			new EventValue<Integer>(0).value(memory -> memory.get(0)), 
			CoreMatchers.equalTo(0)
		);
	}
	
	@Test
	void observerPattern() {
		final EventValue<Integer> number = new EventValue<>();
		final List<Integer> record = new ArrayList<>();
		number.addEvent(value -> record.add(value));
		for (int x = 0; x < 3; x++) {
			number.update(x);
		}
		MatcherAssert.assertThat(
			"The event should be handled.",
			record, 
			CoreMatchers.hasItems(0, 1, 2)
		);
	}
	
	@Test
	void updateViaTask() {
		final EventValue<Integer> number = new EventValue<>();
		number.update((memory, events) -> {
			memory.clear();
			memory.add(0);
		});
		MatcherAssert.assertThat(
			"The value should be updated via task.",
			number.value(), 
			CoreMatchers.equalTo(0)
		);
	}
}
