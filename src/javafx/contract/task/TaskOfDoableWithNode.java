package javafx.contract.task;

import javafx.scene.Node;

/**
 * A strategy for the strategy pattern. <br>
 * It should do something with the Node object received from upper layer.
 * @author Rin
 * @version 1.0.0
 */
public interface TaskOfDoableWithNode {
	/**
	 * @param node JavaFX Node object.
	 * @since 1.0.0
	 */
	void handle(Node node);
}
