package plain.map.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowablePutInMapTest {
	
	@Test
	void validPut() {
		final Map<Integer, Integer> map = new ConcurrentHashMap<>();
		new ThrowablePutInMap<Integer, Integer>(
			0, 0, this.errorMessage, (key, value, localMap) -> true
		).handle(map);
		MatcherAssert.assertThat(
			"The item should've inserted if the validation is met.", 
			map.get(0), 
			CoreMatchers.equalTo(0)
		);
	}

	@Test
	void invalidPut() {
		Assertions.assertThrows(
			RuntimeException.class, 
			() -> {
				new ThrowablePutInMap<Integer, Integer>(
					0, 0, this.errorMessage, (key, value, localMap) -> false
				).handle(new HashMap<>());
			},
			"It should throws exception when it tries to put an item because the validation is not met."
		);
	}
	
	private final String errorMessage = "Invalid.";
	
}
