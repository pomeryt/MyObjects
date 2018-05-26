package javafx.node.task.movement;

import java.util.Map;

import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Camera;
import javafx.scene.Node;

/**
 * It is responsible for moving a node and camera upward in 2 dimensional sense.
 * @author Rin
 * @version 1.0.0
 */
public final class Up2DWithCamera implements TaskOfMoveable {

	/**
	 * @param camera JavaFX Camera to be moved with the node.
	 * @param speed of movement.
	 * @since 1.0.0
	 */
	public Up2DWithCamera(final Camera camera, final double speed) {
		this.camera = camera;
		this.speed = speed;
	}
	
	@Override
	public void handle(final Node node, final Map<String, Runnable> actionMap) {
		final String key = "up2DWithCamera";
		actionMap.put(key, () -> {
			node.setTranslateY(node.getTranslateY() - this.speed);
			this.camera.setTranslateY(this.camera.getTranslateY() - this.speed);
		});
	}

	private final Camera camera;
	private final double speed;
}
