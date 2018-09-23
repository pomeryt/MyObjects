package plain.validation.value;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

class IsValueNullSafeTest {

	@Test
	void testValidCase() {
		// IsValueNullSafe object.
		final IsValueNullSafe<String> isValueNullSafe = new IsValueNullSafe<>();
		
		// Check if "Apple" is valid.
		assertThat(isValueNullSafe.valid("Apple"), new IsEqual<>(true));
	}
	
	@Test
	void testInvalidCase() {
		// IsValueNullSafe object.
		final IsValueNullSafe<String> isValueNullSafe = new IsValueNullSafe<>();

		// Check if null is invalid.
		assertThat(isValueNullSafe.valid(null), new IsEqual<>(false));
	}

}
