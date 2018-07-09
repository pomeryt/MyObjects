package plain.contract.entity;

/**
 * It represents a void method of the entity.
 * @author Rin
 * @version 1.0.0
 * @param <P> The type of something received from the entity.
 */
public interface VoidTaskOfEntity<P> {
	/**
	 * This method should be called by entity object.
	 * @param param Something received from the entity in order to complete the task.
	 * @since 1.0.0
	 */
	void handle(P param);
}
