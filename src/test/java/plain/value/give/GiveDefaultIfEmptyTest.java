package plain.value.give;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class GiveDefaultIfEmptyTest {

	@Test
	void testDefaultValue() {
		MatcherAssert.assertThat(
			"It should give the default value if the memory is empty.",
			new GiveDefaultIfEmpty<String>(this.fruit1).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testExistingValue() {
		final List<String> memory = new ArrayList<>();
		memory.add(this.fruit1);
		MatcherAssert.assertThat(
			"It should give the value from memory.",
			new GiveDefaultIfEmpty<String>(this.fruit2).handle(memory), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}

	@Test
	void testDefaultFromLogic() {
		MatcherAssert.assertThat(
			"It should give default value generated by some logic if the memory is empty.",
			new GiveDefaultIfEmpty<>(() -> this.fruit1).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
}
