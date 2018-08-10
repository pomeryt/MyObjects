package plain.map.task;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapGetValidation;

class DefaultableGiveFromMapTest {

	@Test
	void testSingleInvalidCase() {
		MatcherAssert.assertThat(
			new DefaultableGiveFromMap<String, String>("One", "Apple", this.dummyValidation(false)).handle(new HashMap<>()), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGiveFromMap<String, String>("One", "Apple", 
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(false)
			).handle(new HashMap<>()), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGiveFromMap<String, String>(
				"One", "Banana", this.dummyValidation(true)
			).handle(this.mapWithDefaultPair("One", "Apple")), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGiveFromMap<String, String>(
				"One", "Banana", 
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(true)
			).handle(this.mapWithDefaultPair("One", "Apple")), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	private MapGetValidation<String, String> dummyValidation(final boolean value) {
		return (key, map) -> value;
	}
	
	private Map<String, String> mapWithDefaultPair(final String key, final String value) {
		final Map<String, String> map = new HashMap<>();
		map.put(key, value);
		return map;
	}
}
