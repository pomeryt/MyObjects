package plain.value;

import java.util.List;

import plain.contract.give.GiveableViaTask;
import plain.contract.give.PlainGiveable;
import plain.contract.update.PlainUpdateable;
import plain.contract.update.UpdateableViaTask;

/**
 * A marker interface that helps you compose your objects.
 * A base class is {@link EventValue}.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public interface LiveValue<T> extends PlainGiveable<T>, PlainUpdateable<T>, GiveableViaTask<T, List<T>>, UpdateableViaTask<T> {}
