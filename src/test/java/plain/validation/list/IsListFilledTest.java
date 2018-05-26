package plain.validation.list;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.validation.list.IsListFilled;

class IsListFilledTest {

	@Test
	void testFilledCase() {
		// List that is not empty.
		final List<String> list = new ArrayList<>();
		list.add("Apple");
		
		// Check if the list is valid.
		assertThat(new IsListFilled<String>().valid(list), new IsEqual<>(true));
	}
	
	@Test
	void testEmptyCase() {
		// Empty list.
		final List<String> list = new ArrayList<>();
		
		// Check if the list is invalid.
		assertThat(new IsListFilled<String>().valid(list), new IsEqual<>(false));
	}

}
