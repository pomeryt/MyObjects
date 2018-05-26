package javafx.node;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.contract.DoableWithNodeViaTask;
import javafx.contract.MoveableViaTask;
import javafx.contract.task.TaskOfDoableWithNode;
import javafx.contract.task.TaskOfMoveable;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * This is a node that is capable of moving. <br>
 * It uses the strategy pattern. <br>
 * When it starts to move, it will play the JavaFX Timeline object. <br>
 * The Timeline object will infinitely iterate through the <b>actionMap</b> which has movement logics. <br>
 * For example, if the <b>actionMap</b> contains logics to move up and left, then the node will constantly move up and left direction. <br>
 * The <b>actionMap</b> is encapsulated in this object and can be modified via <b>TaskOfMoveable</b> object. <br>
 * Only one Timeline object exists in this object. <br>
 * The duration of each iteration in Timeline is 1 millisecond.
 * @author Rin
 * @version 1.0.0
 */
public final class MovingNode implements MoveableViaTask, DoableWithNodeViaTask {
	
	/**
	 * @param node to be moved.
	 * @since 1.0.0
	 */
	public MovingNode(final Node node) {
		this.node = node;
	}
	
	@Override
	public void move(final TaskOfMoveable task) {
		if (this.timeline.getKeyFrames().isEmpty()) {
			final KeyFrame keyFrame = new KeyFrame(Duration.ONE, e -> {
				this.actionMap.forEach((key, action) -> {
					action.run();
				});
			});
			this.timeline.getKeyFrames().add(keyFrame);
			this.timeline.setCycleCount(Animation.INDEFINITE);
			this.timeline.play();
		}
		
		task.handle(this.node, this.actionMap);
	}
	
	@Override
	public void execute(TaskOfDoableWithNode task) {
		task.handle(this.node);
	}
	
	private final Node node;
	private final Timeline timeline = new Timeline();
	private final Map<String, Runnable> actionMap = new HashMap<>();
}
