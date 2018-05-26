package javafx.node.task.movement;

import java.util.Map;

import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Node;

/**
 * It is responsible for moving a node upward in 2 dimensional sense.
 * @author Rin
 * @version 1.0.0
 */
public final class Up2D implements TaskOfMoveable {

	/**
	 * @param speed of movement.
	 * @since 1.0.0
	 */
	public Up2D(final double speed) {
		this.speed = speed;
	}
	
	@Override
	public void handle(final Node node, final Map<String, Runnable> actionMap) {
		final String key = "up2D";
		actionMap.put(key, () -> {
			node.setTranslateY(node.getTranslateY() - speed);
		});
	}
	
	private final double speed;
}
