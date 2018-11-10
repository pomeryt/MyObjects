package plain.loop;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.number.count.PlainCount;

class PlainLoopTest {

	@Test
	void shouldRepeatByPositiveInteger() {
		final PlainCount count = new PlainCount();
		new PlainLoop(3, () -> {
			count.increment();
		}).repeat();
		
		assertThat(
			"The loop should increment the count by the given integer.", 
			count.value(), 
			new IsEqual<>(3)
		);
	}
	
	@Test
	void shouldNotRepeatIfNegativeIntegerIsGiven() {
		final PlainCount count = new PlainCount();
		new PlainLoop(-3, () -> {
			count.increment();
		}).repeat();
		
		assertThat("Negative loop should not do anything.", count.value(), new IsEqual<>(0));
	}
	
	@Test
	void shouldNotRepeatIfZeroIsGiven() {
		final PlainCount count = new PlainCount();
		new PlainLoop(0, () -> {
			count.increment();
		}).repeat();
		
		assertThat("Zero loop should not do anything.", count.value(), new IsEqual<>(0));
	}

}
