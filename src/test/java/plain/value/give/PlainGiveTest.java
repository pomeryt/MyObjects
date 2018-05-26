package plain.value.give;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.value.give.PlainGive;

class PlainGiveTest {

	@Test
	void test() {
		// PlainGive object.
		final PlainGive<String> plainGive = new PlainGive<>();
		
		// Prepare a list that has "Apple"
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		
		// Check if PlainGive object returns the first element of the list.
		assertThat(plainGive.handle(memory), new IsEqual<>("Apple"));
	}

}
