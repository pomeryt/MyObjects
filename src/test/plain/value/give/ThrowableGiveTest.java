package test.plain.value.give;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.validation.ListValidation;
import plain.value.give.ThrowableGive;

class ThrowableGiveTest {

	@Test
	void testValidCase() {
		// Validation object that returns true always.
		final ListValidation<String> validation = new ListValidation<String>() {
			@Override
			public boolean valid(final List<String> list) {
				return true;
			}
		};
		
		// ThrowableGive object.
		final ThrowableGive<String> throwableGive = new ThrowableGive<>("Invalid.", validation);
		
		// List that has "Apple"
		final List<String> memory = new ArrayList<>();
		memory.add("Apple");
		
		// Check if ThrowableGive object returns "Apple"
		assertThat(throwableGive.handle(memory), new IsEqual<>("Apple"));
	}
	
	@Test
	void testInvalidCase() {
		// Validation object that return false always.
		final ListValidation<String> validation = new ListValidation<String>() {
			@Override
			public boolean valid(List<String> list) {
				return false;
			}
		};
		
		// ThrowableGive object.
		final ThrowableGive<String> throwableGive = new ThrowableGive<>("Invalid.", validation);
		
		// Check if the ThrowableGive object throws RuntimeException.
		final Throwable exception = assertThrows(RuntimeException.class, () -> throwableGive.handle(new ArrayList<>()));
		
		// Check the message from the exception.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}

}
