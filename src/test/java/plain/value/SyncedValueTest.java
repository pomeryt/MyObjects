package plain.value;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.CountDownLatch;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

@SuppressWarnings("PMD.AvoidCatchingGenericException")
class SyncedValueTest {

	@Test
	public void allMethodsShouldBeExecutedOneByOne() throws InterruptedException {
		final int[] incrementedAmount = new int[1];
		final int incrementAmount = 1;
		final EventValue<Integer> number = new SyncedValue<>(new EventValue<Integer>() {
			@Override
			public Integer value() {
				incrementedAmount[0] = incrementedAmount[0] + incrementAmount;
				return incrementAmount;
			}
			
			@Override
			public void update(final Integer value) {
				incrementedAmount[0] = incrementedAmount[0] + value;
			}
			
			@Override
			public void addEvent(final UpdateEvent<Integer> event) {
				incrementedAmount[0] = incrementedAmount[0] + incrementAmount;
			}
		});
		final int threadAmount = 100;
		final CountDownLatch startingLatch = new CountDownLatch(threadAmount);
		final CountDownLatch endingLatch = new CountDownLatch(threadAmount);
		for (int x = 0; x < threadAmount; x++) {
			new Thread(() -> {
				try {
					startingLatch.countDown();
					startingLatch.await();
					number.value();
					number.update(incrementAmount);
					number.addEvent((oldValue, newValue) -> {
						throw new UnsupportedOperationException("It should not be executed.");
					});
					endingLatch.countDown();
					// @checkstyle IllegalCatchCheck (1 line)
				} catch (final Exception exception) {
					fail("An exception has been thrown while executing the thread.", exception);
				}
			}).start();
		}
		endingLatch.await();
		final int numberOfMethods = 3;
		MatcherAssert.assertThat(
			incrementedAmount[0], 
			CoreMatchers.equalTo(threadAmount * numberOfMethods)
		);
	}
}
