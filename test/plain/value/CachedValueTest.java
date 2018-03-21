package plain.value;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.give.PlainGiveable;
import plain.contract.validation.ListValidation;
import plain.number.count.PlainCount;
import plain.value.CachedValue;
import plain.value.give.ThrowableGive;

class CachedValueTest {

	@Test
	void testNormalBehavior() {
		// PlainCount object to see how many times the logic has been executed.
		// The initial value is 0.
		final PlainCount count = new PlainCount();

		// CachedValue object.
		final CachedValue<String> cache = new CachedValue<>(() -> {
			// Increment the count.
			count.increment();

			// Return new value.
			return "Apple";
		});

		// Check if the value is correct.
		assertThat(cache.value(), new IsEqual<>("Apple"));

		// Call value multiple times.
		cache.value();
		cache.value();
		cache.value();

		// Check if the logic is called only once.
		assertThat(count.value(), new IsEqual<>(1));
	}
	
	@Test
	void testLogicFreeConstructor() {
		// Define a class that has predefined value obtained via some logic.
		// However, the logic will not be executed in the constructor.
		// Instead, the logic will be executed on demand only once.
		// Consecutive call will provide cached value instead of executing the logic again.
		// This practice will remove the side effect caused by the logic.
		final class LogicFreeConstructor {
			
			// Provide PlainCount object to check how many times the logic is executed.
			// This constructor defines the logic to provide the value.
			public LogicFreeConstructor(final PlainCount count) {
				this(() -> {
					// Increment the count when the logic is executed.
					count.increment();
					
					// Return the value from the logic.
					return "Apple";
				});
			}
			
			// Secondary constructor to pass PlainGiveable object.
			public LogicFreeConstructor(final PlainGiveable<String> giveable) {
				this(new CachedValue<String>(giveable));
			}
			
			// Primary constructor.
			public LogicFreeConstructor(final CachedValue<String> cache) {
				this.cache = cache;
			}
			
			// Return value.
			public String value() {
				return this.cache.value();
			}

			private final CachedValue<String> cache;
		}
		
		// PlainCount object to check how many times the logic has been executed.
		// The initial value is 0.
		final PlainCount count = new PlainCount();
		
		// Instantiate the inner class above.
		final LogicFreeConstructor cache = new LogicFreeConstructor(count);
		
		// Obtain the value multiple times.
		cache.value();
		cache.value();
		cache.value();
		
		// Check if the value is correct.
		assertThat(cache.value(), new IsEqual<>("Apple"));
		
		// Check if the logic is executed only once.
		assertThat(count.value(), new IsEqual<>(1));
	}

	@Test
	void testValidation() {
		// Validation object that return false always.
		final ListValidation<String> validation = item -> false;
		
		// Check if the null is invalid.
		final Exception exception = assertThrows(RuntimeException.class, () -> {
			new CachedValue<String>(() -> null).value(new ThrowableGive<String>("Invalid.", validation));
		});
		
		// Check the message.
		assertThat(exception.getMessage(), new IsEqual<>("Invalid."));
	}

}
