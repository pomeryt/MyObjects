package plain.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

class SyncedValueTest {

	@Test
	@SuppressFBWarnings("UC_USELESS_OBJECT")
	void test() {
		final AtomicBoolean running = new AtomicBoolean(false);
		final AtomicInteger overlaps = new AtomicInteger(0);
		final List<Exception> exceptions = Collections.synchronizedList(new ArrayList<>());
		final SyncedValue<String> fruit = new SyncedValue<>("Apple");
		final List<Thread> threads = new ArrayList<>();
		for (int x = 0; x < 1000; x++) {
			threads.add(new Thread(() -> {
				try {
					if (running.get()) {
						overlaps.incrementAndGet();
					}
					running.set(true);
					fruit.update(fruit.value());
					running.set(false);
				} catch (Exception exception) {
					exceptions.add(exception);
				}
			}));
		}
		threads.forEach(Thread::start);
		
		MatcherAssert.assertThat(
			"Overlaps should be greater than 0.", 
			overlaps.get(), 
			Matchers.greaterThan(0)
		);
		MatcherAssert.assertThat(
			"No exception should be thrown.",
			exceptions.size(), 
			Matchers.is(0)
		);
		MatcherAssert.assertThat(
			"The value should be returned.",
			fruit.value(), 
			Matchers.is("Apple")
		);
	}

}
