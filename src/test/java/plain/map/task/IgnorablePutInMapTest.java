package plain.map.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapPutValidation;

class IgnorablePutInMapTest {

	@Test
	void testSingleInvalidCase() {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put(this.key1, this.fruit1);
		new IgnorablePutInMap<String, String>(this.key1, this.fruit2, this.dummyValidation(false)
		).handle(map);
		MatcherAssert.assertThat(
			map.get(this.key1), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put(this.key1, this.fruit1);
		new IgnorablePutInMap<String, String>(this.key1, this.fruit2, 
			this.dummyValidation(true), 
			this.dummyValidation(true), 
			this.dummyValidation(false)
		).handle(map);
		MatcherAssert.assertThat(
			map.get(this.key1), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleValidCase() {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put(this.key1, this.fruit1);
		new IgnorablePutInMap<String, String>(this.key1, this.fruit2, this.dummyValidation(true)
		).handle(map);
		MatcherAssert.assertThat(
			map.get(this.key1), 
			CoreMatchers.equalTo(this.fruit2)
		);
	}
	
	@Test
	void testMultipleValidCase() {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put(this.key1, this.fruit1);
		new IgnorablePutInMap<String, String>(this.key1, this.fruit2, 
			this.dummyValidation(true), 
			this.dummyValidation(true), 
			this.dummyValidation(true)
		).handle(map);
		MatcherAssert.assertThat(
			map.get(this.key1), 
			CoreMatchers.equalTo(this.fruit2)
		);
	}
	
	private MapPutValidation<String, String> dummyValidation(final boolean valid) {
		return (key, value, map) -> valid;
	}
	
	private final String key1 = "One";
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
}
