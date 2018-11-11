package plain.map.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapGetValidation;

class DefaultableGiveFromMapTest {

	@Test
	void testSingleInvalidCase() {
		MatcherAssert.assertThat(
			"It should give default value if the validation is not met.",
			new DefaultableGiveFromMap<String, String>(this.key1, this.fruit1, this.dummyValidation(false)).handle(new HashMap<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		MatcherAssert.assertThat(
			"It should give default value if any validation is not met.",
			new DefaultableGiveFromMap<String, String>(this.key1, this.fruit1, 
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(false)
			).handle(new HashMap<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleValidCase() {
		MatcherAssert.assertThat(
			"It should give value from the map if the validation is met.",
			new DefaultableGiveFromMap<String, String>(
				this.key1, this.fruit2, this.dummyValidation(true)
			).handle(this.mapWithDefaultPair(this.key1, this.fruit1)), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testMultipleValidCase() {
		MatcherAssert.assertThat(
			"It should give value from the map if all validations are met.",
			new DefaultableGiveFromMap<String, String>(
				this.key1, this.fruit2, 
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(true)
			).handle(this.mapWithDefaultPair(this.key1, this.fruit1)), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	private MapGetValidation<String, String> dummyValidation(final boolean value) {
		return (key, map) -> value;
	}
	
	private Map<String, String> mapWithDefaultPair(final String key, final String value) {
		final Map<String, String> map = new ConcurrentHashMap<>();
		map.put(key, value);
		return map;
	}
	
	private final String key1 = "One";
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
}
