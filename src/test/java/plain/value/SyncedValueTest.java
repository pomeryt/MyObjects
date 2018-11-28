package plain.value;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT", justification = "Return values are not important to the thread-safey testing.")
class SyncedValueTest {

	@Test
	public void proveThreadSafetyByManipulatingNonThreadSafeList() throws InterruptedException {
		final List<Integer> nonThreadSafeList = new ArrayList<>();
		final EventValue<Integer> number = new SyncedValue<>(
			new EventValue<Integer>() {
				@Override
				public Integer value() {
					return nonThreadSafeList.get(nonThreadSafeList.size() - 1);
				}

				@Override
				public void update(final Integer value) {
					nonThreadSafeList.add(value);
				}

				@Override
				public void addEvent(final UpdateEvent<Integer> event) {
					nonThreadSafeList.remove(0);
				}
			}
		);
		final int totalThreadAmount = 300;
		final int remainingThreadAmount = 100;
		final int addingThreadAmount = totalThreadAmount - remainingThreadAmount;
		final int removingThreadAmount = totalThreadAmount - addingThreadAmount;
		final CountDownLatch latch = new CountDownLatch(totalThreadAmount);
		this.addItemsInThreads(number, addingThreadAmount, latch);
		this.removeItemsInThreads(number, removingThreadAmount, latch);
		latch.await();
		MatcherAssert.assertThat(
			nonThreadSafeList.size(), 
			CoreMatchers.equalTo(remainingThreadAmount)
		);
	}

	private void addItemsInThreads(final EventValue<Integer> number, final int amount, final CountDownLatch outerLatch) {
		final CountDownLatch latch = new CountDownLatch(1);
		final List<Thread> threads = new ArrayList<Thread>();
		final Random random = new Random();
		for (int x = 0; x < amount; x++) {
			threads.add(new Thread(() -> {
				try {
					latch.await();
					number.update(random.nextInt());
					number.value();
					outerLatch.countDown();
				} catch (final InterruptedException exception) {
					fail("The thread has been interrupted.", exception);
				}
			}));
		}
		threads.forEach(thread -> thread.start());
		latch.countDown();
	}
	
	private void removeItemsInThreads(final EventValue<Integer> number, final int amount, final CountDownLatch outerLatch) {
		final CountDownLatch latch = new CountDownLatch(1);
		final List<Thread> threads = new ArrayList<>();
		for (int x = 0; x < amount; x++) {
			threads.add(new Thread(() -> {
				try {
					latch.await();
					number.addEvent((oldValue, newValue) -> {
						throw new UnsupportedOperationException(
							"You are not supposed to use this method."
						);
					});
					number.value();
					outerLatch.countDown();
				} catch (final InterruptedException exception) {
					fail("The thread has been interrupted.", exception);
				}
			}));
		}
		threads.forEach(thread -> thread.start());
		latch.countDown();
	}
}
