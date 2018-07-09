
package plain.contract.entity;

/**
 * It represents a method of the entity that returns something.
 * @author Rin
 * @version 1.0.0
 * @param <T> The type of return value.
 * @param <P> The type of something received from the entity.
 */
public interface ReturnTaskOfEntity<T, P> {
	/**
	 * This method should be called by entity object.
	 * @param param Something received from the entity in order to complete the task.
	 * @return The result of requested task.
	 * @since 1.0.0
	 */
	T handle(P param);
}
