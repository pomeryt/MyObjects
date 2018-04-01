package javafx.contract.task;

import java.util.Map;

import javafx.scene.Node;

/**
 * A strategy for the strategy pattern. <br>
 * It is responsible for moving the node.
 * @author Rin
 * @version 1.0.0
 */
public interface TaskOfMoveable {
	/**
	 * This method is primarily created for <b>MovingNode</b> class. <br>
	 * The <b>MovingNode</b> will constantly iterate through the <b>actionMap</b>. <br>
	 * When you put an action to the <b>actionMap</b>, it will be executed during the iteration. <br>
	 * The key for <b>actionMap</b> should be similar to class name in order to avoid key duplication by mistake. <br>
	 * For example, if the class name is "Up2D", then the key should be "up2D" (Everything is same except the first character is lower-cased letter).
	 * @param node JavaFX Node.
	 * @param 
	 * actionMap The <b>MvoingNode</b> object will iterate through this map infinitely.
	 * @since 1.0.0
	 */
	void handle(Node node, Map<String, Runnable> actionMap);
}
