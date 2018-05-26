package plain.contract.task;

/**
 * A task that takes one parameter and return something. <br>
 * This object is intended to be a method of another object. <br>
 * For example, you may want to have many methods in some object like this: <br>
 * <pre>
 * final Human human = new Human();
 * 
 * System.out.println(human.name());
 * System.out.println(human.age());
 * System.out.println(human.occupation());
 * ...
 * </pre>
 * Instead of having many methods, you can have single method that executes a task like this: <br>
 * <pre>
 * final Human human = new Human();
 * 
 * &#47;&#47; You pass some task object.
 * System.out.println(human.retrieve(new Name()));
 * System.out.println(human.retrieve(new Age()));
 * System.out.println(human.retrieve(new Occupation()));
 * ...
 * </pre>
 * @author Rin
 * @version 1.0.0
 * @param <R> Return type.
 * @param <P> Parameter type.
 */
public interface ReturnTask<R, P> {
	/**
	 * You should provide a parameter. <br>
	 * This object will take that parameter to complete the task. <br>
	 * For example, let's say you want this object to return the first element of list for you. <br>
	 * To do that, you need to provide some list so this object can retrieve the first element like this: <br>
	 * <pre>
	 * &#47;&#47; FirstElement class implements this interface.
	 * new FirstElement().handle(list);
	 * </pre>
	 * @param param This method will do some work using that parameter.
	 * @return Something.
	 * @since 1.0.0
	 */
	R handle(P param);
}
