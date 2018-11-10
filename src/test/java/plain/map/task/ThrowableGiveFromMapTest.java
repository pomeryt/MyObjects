package plain.map.task;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableGiveFromMapTest {
	
	@Test
	void validGive() {
		final Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		MatcherAssert.assertThat(
			"It should give the value if the validation is met.",
			new ThrowableGiveFromMap<Integer, Integer>(
				0, this.errorMessage, (key, value) -> true
			).handle(map), 
			CoreMatchers.equalTo(0)
		);
	}
	
	@Test
	void invalidGive() {
		Assertions.assertThrows(
			RuntimeException.class, 
			() -> {
				new ThrowableGiveFromMap<Integer, Integer>(
					0, this.errorMessage, (key, value) -> false
				).handle(new HashMap<>());
			},
			"It should throw exception if the validation is not met."
		);
	}
	
	private final String errorMessage = "Invalid.";
}
