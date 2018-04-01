package javafx.node.task.movement;

import java.util.Map;

import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Node;

/**
 * It is responsible for moving a node downward in 2 dimensional sense.
 * @author Rin
 * @version 1.0.0
 */
public final class Down2D implements TaskOfMoveable {

	/**
	 * @param speed of movement.
	 * @since 1.0.0
	 */
	public Down2D(final double speed) {
		this.speed = speed;
	}
	
	@Override
	public void handle(final Node node, final Map<String, Runnable> actionMap) {
		final String key = "down2D";
		actionMap.put(key, () -> {
			node.setTranslateY(node.getTranslateY() + speed);
		});
	}

	private final double speed;
}
