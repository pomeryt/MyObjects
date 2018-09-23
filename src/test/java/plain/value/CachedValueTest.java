package plain.value;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.number.count.PlainCount;

class CachedValueTest {
	
	@Test
	void obtainValueViaDefault() {
		MatcherAssert.assertThat(
			new CachedValue<Integer>(() -> 0).value(), 
			CoreMatchers.equalTo(0)
		);
	}
	
	@Test
	void obtainValueViaTask() {
		MatcherAssert.assertThat(
			new CachedValue<Integer>(() -> 0).value(memory -> memory.get(0)), 
			CoreMatchers.equalTo(0)
		);
	}
	
	@Test
	void valueIsCached() {
		final PlainCount count = new PlainCount();
		final CachedValue<Integer> cached = new CachedValue<>(() -> {
			count.increment();
			return 0;
		});
		for (int x = 0; x < 3; x++) {
			cached.value();
		}
		MatcherAssert.assertThat(count.value(), CoreMatchers.equalTo(1));
	}

}
