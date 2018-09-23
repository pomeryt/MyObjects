package plain.map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormalMapTest {
	
	@Test
	void defaultRegister() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		formalMap.register(0, 0);
		MatcherAssert.assertThat(formalMap.value(0), CoreMatchers.equalTo(0));
	}
	
	@Test
	void registerViaTask() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		formalMap.register(map -> map.put(0, 0));
		MatcherAssert.assertThat(formalMap.value(0), CoreMatchers.equalTo(0));
	}
	
	@Test
	void defaultUpdate() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		formalMap.register(0, 0);
		formalMap.update(0, 1);
		MatcherAssert.assertThat(formalMap.value(0), CoreMatchers.equalTo(1));
	}
	
	@Test
	void updateViaTask() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		formalMap.register(0, 0);
		formalMap.update(map -> map.put(0, 1));
		MatcherAssert.assertThat(formalMap.value(0), CoreMatchers.equalTo(1));
	}
	
	@Test
	void obtainValueViaTask() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		formalMap.register(0, 0);
		MatcherAssert.assertThat(formalMap.value(map -> map.get(0)), CoreMatchers.equalTo(0));
	}
	
	@Test
	void interateKeys() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		for(int x = 0; x < 3; x++) {
			formalMap.register(x, x);
		}
		MatcherAssert.assertThat(formalMap.keys(), CoreMatchers.hasItems(0, 1, 2));
	}
	
	@Test
	void obtainValueBeforeRegister() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		Assertions.assertThrows(RuntimeException.class, () -> formalMap.value(0));
	}
	
	@Test
	void updateBeforeRegister() {
		final FormalMap<Integer, Integer> formalMap = new FormalMap<>();
		Assertions.assertThrows(RuntimeException.class, () -> formalMap.update(0, 0));
	}

}
