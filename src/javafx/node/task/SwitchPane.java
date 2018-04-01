package javafx.node.task;

import javafx.contract.task.TaskOfDoableWithNode;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * It is responsible for moving a node to different container.
 * @author Rin
 * @version 1.0.0
 */
public final class SwitchPane implements TaskOfDoableWithNode {

	/**
	 * @param pane Move a node to this pane.
	 * @since 1.0.0
	 */
	public SwitchPane(final Pane pane) {
		this.pane = pane;
	}
	
	@Override
	public void handle(final Node node) {
		if (this.pane.getChildren().contains(node) == false) {
			this.pane.getChildren().add(node);
		}
	}

	private final Pane pane;
}
