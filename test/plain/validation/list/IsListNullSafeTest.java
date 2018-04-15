package plain.validation.list;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.validation.list.IsListNullSafe;

class IsListNullSafeTest {

	@Test
	void testValidCase() {
		// IsListNullSafe object.
		final IsListNullSafe<String> isListNullSafe = new IsListNullSafe<>();
		
		// List that does not have null.
		final List<String> list = new ArrayList<>();
		list.add("Apple");
		
		// Check if the list is valid.
		assertThat(isListNullSafe.valid(list), new IsEqual<>(true));
	}
	
	@Test
	void testInvalidCase() {
		// IsListNullSafe object.
		final IsListNullSafe<String> isListNullSafe = new IsListNullSafe<>();
		
		// List that contains null.
		final List<String> list = new ArrayList<>();
		list.add(null);
		
		// Check if the list is invalid.
		assertThat(isListNullSafe.valid(list), new IsEqual<>(false));
	}

}
