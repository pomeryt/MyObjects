package plain.map.task;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
class ThrowablePutInMapTest {
	
	@Test
	void validPut() {
		final Map<Integer, Integer> map = new HashMap<>();
		new ThrowablePutInMap<Integer, Integer>(
			0, 0, this.errorMessage, (key, value, localMap) -> true
		).handle(map);
		MatcherAssert.assertThat(map.get(0), CoreMatchers.equalTo(0));
	}

	@Test
	void invalidPut() {
		Assertions.assertThrows(
			RuntimeException.class, 
			() -> {
				new ThrowablePutInMap<Integer, Integer>(
					0, 0, this.errorMessage, (key, value, localMap) -> false
				).handle(new HashMap<>());
			}
		);
	}
	
	private final String errorMessage = "Invalid.";
	
}
