package plain.contract.task;

/**
 * A task object that takes one parameter and does not return anything. <br>
 * This object is intended to be a method of another object. <br>
 * For example, you may want to have many methods in some object like this: <br>
 * <pre>
 * final Human human = new Human();
 * 
 * human.talk();
 * human.run();
 * human.cook();
 * ...
 * </pre>
 * Instead of having many methods, you can have single method that executes a task like this: <br>
 * <pre>
 * final Human human = new Human();
 * 
 * &#47;&#47; You pass some task object.
 * human.execute(new Talk());
 * human.execute(new Run());
 * human.execute(new Cook());
 * ...
 * </pre>
 * @author Rin
 * @version 1.0.0
 * @param <P> The type of parameter you will provide to this object.
 */
public interface VoidTask<P> {
	/**
	 * You should provide a parameter. <br>
	 * This object will take that parameter to complete the task. <br>
	 * For example, let's say you want this object to print something for you. <br>
	 * To do that, you need to provide some text to be printed like this: <br>
	 * <pre>
	 * &#47;&#47; Print class implements this interface.
	 * new Print().handle("some text");
	 * </pre>
	 * @param param This method will do some work using that parameter.
	 * @since 1.0.0
	 */
	void handle(P param);
}
