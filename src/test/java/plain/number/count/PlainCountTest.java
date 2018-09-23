package plain.number.count;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class PlainCountTest {
	
	@Test
	void incrementFromDefault() {
		final PlainCount count = new PlainCount();
		for (int x = 0; x < 3; x++) {
			count.increment();
		}
		MatcherAssert.assertThat(count.value(), CoreMatchers.equalTo(3));
	}
	
	@Test
	void incrementFromGiven() {
		final PlainCount count = new PlainCount(3);
		for (int x = 0; x < 3; x++) {
			count.increment();
		}
		MatcherAssert.assertThat(count.value(), CoreMatchers.equalTo(6));
	}
}
