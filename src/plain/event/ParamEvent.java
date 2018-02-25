package plain.event;

/**
 * An event with a parameter. <br />
 * It is a functional interface.
 * @author Rin
 * @version 1.0.0
 * @param <T>
 */
public interface ParamEvent<T> {
	/**
	 * Handle an event with the parameter.
	 * @param eventParam
	 * @since 1.0.0
	 */
	void handle(T eventParam);
}
