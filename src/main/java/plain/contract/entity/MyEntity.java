package plain.contract.entity;

/**
 * This object does not directly handle your request. <br>
 * Instead, it delegates to the task object. <br>
 * The intention is to avoid making too many methods in an object. <br>
 * By delegating the actual work, the entity is capable of doing many things while having few methods. <br>
 * This object must provide some necessary thing to the task object. <br>
 * The "necessary thing" may be the state of this object.
 * @author Rin
 * @version 1.0.0
 * @param <P> The type of something to be passed to the task object.
 */
public interface MyEntity<P> {
	/**
	 * @param task It handles your request without returning anything.
	 * @since 1.0.0
	 */
	void workOn(VoidTaskOfEntity<P> task);
	
	/**
	 * @param task It handles your request and return something.
	 * @return The result of requested task.
	 * @since 1.0.0
	 */
	<T> T valueOf(ReturnTaskOfEntity<T, P> task);
}
