package plain.event;

/**
 * An event without parameter. <br />
 * It is a functional interface.
 * @author Rin
 * @version 1.0.0
 */
public interface PlainEvent {
	/**
	 * Handle an event.
	 * @since 1.0.0
	 */
	void handle();
}
