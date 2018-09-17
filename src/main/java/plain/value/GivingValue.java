package plain.value;

import java.util.List;

import plain.contract.give.GiveableViaTask;
import plain.contract.give.PlainGiveable;

/**
 * A marker interface that helps you compose your objects.
 * A base class is {@link CachedValue}.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of value.
 */
public interface GivingValue<T> extends PlainGiveable<T>, GiveableViaTask<T, List<T>> {}
