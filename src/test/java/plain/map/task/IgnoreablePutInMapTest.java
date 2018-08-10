package plain.map.task;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.contract.validation.MapPutValidation;

class IgnoreablePutInMapTest {

	@Test
	void testSingleInvalidCase() {
		final Map<String, String> map = new HashMap<>();
		map.put("One", "Apple");
		new IgnoreablePutInMap<String, String>("One", "Banana", this.dummyValidation(false)
		).handle(map);
		MatcherAssert.assertThat(
			map.get("One"), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		final Map<String, String> map = new HashMap<>();
		map.put("One", "Apple");
		new IgnoreablePutInMap<String, String>("One", "Banana", 
			this.dummyValidation(true), 
			this.dummyValidation(true), 
			this.dummyValidation(false)
		).handle(map);
		MatcherAssert.assertThat(
			map.get("One"), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleValidCase() {
		final Map<String, String> map = new HashMap<>();
		map.put("One", "Apple");
		new IgnoreablePutInMap<String, String>("One", "Banana", this.dummyValidation(true)
		).handle(map);
		MatcherAssert.assertThat(
			map.get("One"), 
			CoreMatchers.equalTo("Banana")
		);
	}
	
	@Test
	void testMultipleValidCase() {
		final Map<String, String> map = new HashMap<>();
		map.put("One", "Apple");
		new IgnoreablePutInMap<String, String>("One", "Banana", 
			this.dummyValidation(true), 
			this.dummyValidation(true), 
			this.dummyValidation(true)
		).handle(map);
		MatcherAssert.assertThat(
			map.get("One"), 
			CoreMatchers.equalTo("Banana")
		);
	}
	
	private MapPutValidation<String, String> dummyValidation(final boolean value) {
		return (k, v, map) -> value;
	}
}
