package plain.validation.list;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

class IsListNullSafeTest {

	@Test
	void testValidCase() {
		// IsListNullSafe object.
		final IsListNullSafe<String> nullSafeCheck = new IsListNullSafe<>();
		
		// List that does not have null.
		final List<String> list = new ArrayList<>();
		list.add("Apple");
		
		// Check if the list is valid.
		assertThat(
			"The list should be valid if it doesn't have null.",
			nullSafeCheck.valid(list), 
			new IsEqual<>(true)
		);
	}
	
	@Test
	void testInvalidCase() {
		// IsListNullSafe object.
		final IsListNullSafe<String> nullSafeCheck = new IsListNullSafe<>();
		
		// List that contains null.
		final List<String> list = new ArrayList<>();
		list.add(null);
		
		// Check if the list is invalid.
		assertThat(
			"The should be invalid if it has null.",
			nullSafeCheck.valid(list), 
			new IsEqual<>(false)
		);
	}

}
