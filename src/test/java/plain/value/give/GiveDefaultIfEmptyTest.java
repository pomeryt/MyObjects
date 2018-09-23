package plain.value.give;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
class GiveDefaultIfEmptyTest {

	@Test
	void testDefaultValue() {
		MatcherAssert.assertThat(
			new GiveDefaultIfEmpty<String>(this.fruit1).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testExistingValue() {
		final List<String> memory = new ArrayList<>();
		memory.add(this.fruit1);
		MatcherAssert.assertThat(
			new GiveDefaultIfEmpty<String>(this.fruit2).handle(memory), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}

	@Test
	void testDefaultFromLogic() {
		MatcherAssert.assertThat(
			new GiveDefaultIfEmpty<>(() -> this.fruit1).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
}
