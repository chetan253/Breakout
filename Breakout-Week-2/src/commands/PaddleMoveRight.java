package commands;

import constants.Constants;
import entity.Paddle;
import entity.Paddle.MOVE;

public class PaddleMoveRight implements Command, Constants {

	Paddle paddle;

	public PaddleMoveRight(Paddle paddle) {
		this.paddle = paddle;
	}

	@Override
	public void execute() {
		if (paddle.getX() <= reflectPaddRightWall) {
			paddle.location.x = paddle.location.x + paddle.SPEED;
		}
		paddle.setMovement(MOVE.STABLE);
	}

	@Override
	public void undo() {
		if (paddle.getX() >= reflectPaddLeftWall) {
			paddle.location.x = paddle.location.x - paddle.SPEED;
		}
		paddle.setMovement(MOVE.STABLE);
	}

}
