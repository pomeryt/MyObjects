package plain.contract.event;

/**
 * An event with a parameter. <br />
 * It is a functional interface.
 * @author Rin
 * @version 2.0.0
 * @param <T>
 */
public interface ParamEvent<T> {
	/**
	 * Handle an event with the parameter.
	 * @param eventParam A parameter obtained from the event.
	 * @since 1.0.0
	 */
	void handle(T eventParam);
}
