package javafx.node.task.movement;

import java.util.Map;

import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Camera;
import javafx.scene.Node;

/**
 * It is responsible for moving a node and camera leftward in 2 dimensional sense.
 * @author Rin
 * @version 1.0.0
 */
public final class Left2DWithCamera implements TaskOfMoveable {

	/**
	 * @param camera JavaFX Camera to be moved with the node.
	 * @param speed of movement.
	 * @since 1.0.0
	 */
	public Left2DWithCamera(final Camera camera, final double speed) {
		this.camera = camera;
		this.speed = speed;
	}
	
	@Override
	public void handle(final Node node, final Map<String, Runnable> actionMap) {
		final String key = "left2DWithCamera";
		actionMap.put(key, () -> {
			node.setTranslateX(node.getTranslateX() - this.speed);
			this.camera.setTranslateX(this.camera.getTranslateX() - this.speed);
		});
	}

	private final Camera camera;
	private final double speed;
}
