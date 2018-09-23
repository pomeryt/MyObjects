package plain.map.task;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
class ThrowableGiveFromMapTest {
	
	@Test
	void validGive() {
		final Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		MatcherAssert.assertThat(
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
			}
		);
	}
	
	private final String errorMessage = "Invalid.";
}
