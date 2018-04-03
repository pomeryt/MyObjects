package plain.value.validation;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

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
