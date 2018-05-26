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
 * a.addEvent(param -&#62; {System.out.println(param)});
 * <br> &#47;&#47; The 'param' will be printed when the event is triggered.
 * a.trigger();
 * </pre>
 * @author Rin
 * @version 2.0.1
 * @param <T> When the event is triggered, some parameter will be passed.
 */
public interface ParamEvent<T> {
	/**
	 * Handle an event with the parameter.
	 * @param eventParam A parameter obtained from the event.
	 * @since 1.0.0
	 */
	void handle(T eventParam);
}
