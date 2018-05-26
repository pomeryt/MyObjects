package javafx.node.task.movement;

import java.util.Map;

import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Node;

/**
 * It is responsible for moving a node rightward in 2 dimensional sense.
 * @author Rin
 * @version 1.0.0
 */
public final class Right2D implements TaskOfMoveable {

	/**
	 * @param speed of movement.
	 * @since 1.0.0
	 */
	public Right2D(final double speed) {
		this.speed = speed;
	}
	
	@Override
	public void handle(final Node node, final Map<String, Runnable> actionMap) {
		final String key = "right2D";
		actionMap.put(key, () -> {
			node.setTranslateX(node.getTranslateX() + speed);
		});
	}

	private final double speed;
}
