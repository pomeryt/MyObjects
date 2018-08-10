package plain.value.give;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

class DefaultIfEmptyGiveTest {

	@Test
	void testDefaultValue() {
		MatcherAssert.assertThat(
			new DefaultIfEmptyGive<String>("Apple").handle(new ArrayList<>()), 
			CoreMatchers.equalTo("Apple")
		);
	}
	
	@Test
	void testExistingValue() {
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		MatcherAssert.assertThat(
			new DefaultIfEmptyGive<String>("Banana").handle(memory), 
			CoreMatchers.equalTo("Apple")
		);
	}

}
