package plain.value.give;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import plain.contract.validation.ListValidation;

class DefaultableGiveTest {

	@Test
	void testSingleInvalidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>("Apple", this.dummyValidation(false)).handle(new ArrayList<>()), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleInvalidAmongMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>("Apple",
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(false)
			).handle(new ArrayList<>()), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testSingleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>("Banana",
				this.dummyValidation(true)
			).handle(this.memory("Apple")), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testMultipleValidCase() {
		MatcherAssert.assertThat(
			new DefaultableGive<String>("Banana",
				this.dummyValidation(true), 
				this.dummyValidation(true), 
				this.dummyValidation(true)
			).handle(this.memory("Apple")), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	private ListValidation<String> dummyValidation(final boolean value) {
		return list -> value;
	}
	
	private List<String> memory(final String value) {
		return new ArrayList<>(Arrays.asList(value));
	}
}
