package plain.value.give;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import plain.contract.validation.ListValidation;

@SuppressFBWarnings("SS_SHOULD_BE_STATIC")
class DefaultableGiveTest {

	@Test
	void testSingleInvalidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>(this.fruit1, this.dummyValidation(false)).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>(this.fruit1,
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(false)
			).handle(new ArrayList<>()), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testSingleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>(this.fruit2,
				this.dummyValidation(true)
			).handle(this.memory(this.fruit1)), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	@Test
	void testMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>(this.fruit2,
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(true)
			).handle(this.memory(this.fruit1)), 
			CoreMatchers.equalTo(this.fruit1)
		);
	}
	
	private ListValidation<String> dummyValidation(final boolean value) {
		return list -> value;
	}
	
	private List<String> memory(final String value) {
		return new ArrayList<>(Arrays.asList(value));
	}
	
	private final String fruit1 = "Apple";
	private final String fruit2 = "Banana";
}
