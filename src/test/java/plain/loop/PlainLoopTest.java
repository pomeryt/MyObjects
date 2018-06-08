package plain.loop;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.number.count.PlainCount;

class PlainLoopTest {

	@Test
	void testPositiveLoop() {
		final PlainCount count = new PlainCount();
		new PlainLoop(3, () -> {
			count.increment();
		}).repeat();
		
		assertThat(count.value(), new IsEqual<>(3));
	}
	
	@Test
	void testNegativeLoop() {
		final PlainCount count = new PlainCount();
		new PlainLoop(-3, () -> {
			count.increment();
		}).repeat();
		
		assertThat(count.value(), new IsEqual<>(0));
	}
	
	@Test
	void testZeroLoop() {
		final PlainCount count = new PlainCount();
		new PlainLoop(0, () -> {
			count.increment();
		}).repeat();
		
		assertThat(count.value(), new IsEqual<>(0));
	}

}
