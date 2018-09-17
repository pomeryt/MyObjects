package plain.number.count;

import plain.contract.give.PlainGiveable;
import plain.contract.number.PlainIncrementable;

/**
 * A marker interface that helps you compose your objects.
 * A base class is {@link PlainCount}.
 * @author Rin
 * @version 1.0.0
 */
public interface SimpleCount extends PlainIncrementable, PlainGiveable<Integer> {}
