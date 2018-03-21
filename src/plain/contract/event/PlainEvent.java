package plain.contract.event;

/**
 * An event handler for observer pattern. <br>
 * You add this object to some object A that triggers the event. <br>
 * When the object A triggers the event, this object will handle the situation. <br>
 * This is a functional interface. <br><br>
 * Example:
 * <pre>
 * &#47;&#47; Some object A.
 * final A a = new A();
 * <br> &#47;&#47; You add ParamEvent&#60;T&#62; object to the object 'a'.
 * a.addEvent( () -&#62; {System.out.println("Event triggered!")});
 * <br> &#47;&#47; "Event triggered!" will be printed when the event is triggered.
 * a.trigger();
 * </pre>
 * @author Rin
 * @version 2.0.1
 */
public interface PlainEvent {
	/**
	 * Handle an event.
	 * @since 1.0.0
	 */
	void handle();
}
