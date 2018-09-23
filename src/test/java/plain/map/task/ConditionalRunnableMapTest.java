package plain.map.task;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import plain.contract.map.GiveableMap;
import plain.contract.task.ReturnTask;
import plain.contract.validation.GiveableMapValidation;
import plain.number.count.PlainCount;

class ConditionalRunnableMapTest {

	@Test
	void testValidCaseWithSingleValidation() {
		// A counter to show how many times the runnable has been executed.
		final PlainCount count = new PlainCount();
		
		// This will increment the counter by 1 when it is executed.
		final Runnable runnable = () -> count.increment();
		
		// Always valid condition.
		final GiveableMapValidation<String, Boolean> validation = giveableMap -> true;
		
		// Dummy GiveableMap to fill the parameter.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
			@Override
			public Boolean value(final String key) {
				return true;
			}

			@Override
			public Boolean value(final ReturnTask<Boolean, Map<String, Boolean>> task) {
				return true;
			}

			@Override
			public Set<String> keys() {
				return new HashSet<String>();
			}
		};
		
		// Run the main task.
		final ConditionalRunForMap<String, Boolean> conditionalRun = new ConditionalRunForMap<>(runnable, validation);
		conditionalRun.handle(giveableMap);
		
		// Check if the runnable has been executed correctly.
		assertThat(count.value(), new IsEqual<>(1));
	}
	
	@Test
	void testValidCaseWithMultipleValidations() {
		// A counter to show how many times the runnable has been executed.
		final PlainCount count = new PlainCount();
		
		// This will increment the counter by 1 when it is executed.
		final Runnable runnable = () -> count.increment();
		
		// Always valid conditions.
		final GiveableMapValidation<String, Boolean> validation1 = giveableMap -> true;
		final GiveableMapValidation<String, Boolean> validation2 = giveableMap -> true;
		
		// Dummy GiveableMap to fill the parameter.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
			@Override
			public Boolean value(final String key) {
				return true;
			}

			@Override
			public Boolean value(final ReturnTask<Boolean, Map<String, Boolean>> task) {
				return true;
			}

			@Override
			public Set<String> keys() {
				return new HashSet<String>();
			}

			
		};
		
		// Run the main task.
		final ConditionalRunForMap<String, Boolean> conditionalRun = new ConditionalRunForMap<>(runnable, validation1, validation2);
		conditionalRun.handle(giveableMap);
		
		// Check if the runnable has been executed correctly.
		assertThat(count.value(), new IsEqual<>(1));
	}
	
	@Test
	void testInvalidCaseWithSingleValidation() {
		// A counter to show how many times the runnable has been executed.
		final PlainCount count = new PlainCount();
		
		// This will increment the counter by 1 when it is executed.
		final Runnable runnable = () -> count.increment();
		
		// Always invalid condition.
		final GiveableMapValidation<String, Boolean> validation = giveableMap -> false;
		
		// Dummy GiveableMap to fill the parameter.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
			@Override
			public Boolean value(final String key) {
				return true;
			}

			@Override
			public Boolean value(final ReturnTask<Boolean, Map<String, Boolean>> task) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public Set<String> keys() {
				return new HashSet<String>();
			}

			
		};
		
		// Run the main task.
		final ConditionalRunForMap<String, Boolean> conditionalRun = new ConditionalRunForMap<>(runnable, validation);
		conditionalRun.handle(giveableMap);
		
		// Check if the runnable has not been executed.
		assertThat(count.value(), new IsEqual<>(0));
	}
	
	@Test
	void testInvalidCaseWithMultipleValidations() {
		// A counter to show how many times the runnable has been executed.
		final PlainCount count = new PlainCount();
		
		// This will increment the counter by 1 when it is executed.
		final Runnable runnable = () -> count.increment();
		
		// Valid and invalid conditions.
		final GiveableMapValidation<String, Boolean> validation1 = giveableMap -> true;
		final GiveableMapValidation<String, Boolean> validation2 = giveableMap -> false;
		
		// Dummy GiveableMap to fill the parameter.
		final GiveableMap<String, Boolean> giveableMap = new GiveableMap<String, Boolean>() {
			@Override
			public Boolean value(final String key) {
				return true;
			}

			@Override
			public Boolean value(final ReturnTask<Boolean, Map<String, Boolean>> task) {
				return true;
			}

			@Override
			public Set<String> keys() {
				return new HashSet<String>();
			}

		};
		
		// Run the main task.
		final ConditionalRunForMap<String, Boolean> conditionalRun = new ConditionalRunForMap<>(runnable, validation1, validation2);
		conditionalRun.handle(giveableMap);
		
		// Check if the runnable has not been executed.
		assertThat(count.value(), new IsEqual<>(0));
	}

}
