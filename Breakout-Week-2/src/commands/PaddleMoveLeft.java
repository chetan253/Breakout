package commands;

import constants.Constants;
import entity.Paddle;
import entity.Paddle.MOVE;

public class PaddleMoveLeft implements Command, Constants {

	Paddle paddle;

	public PaddleMoveLeft(Paddle paddle) {
		this.paddle = paddle;
	}

	@Override
	public void execute() {
		if (paddle.getX() >= reflectPaddLeftWall) {
			paddle.location.x = paddle.location.x - paddle.SPEED;
		}
		paddle.setMovement(MOVE.STABLE);
	}

	@Override
	public void undo() {
		System.out.println("Replay/Undo paddle move right");
		if (paddle.getX() <= reflectPaddRightWall) {
			paddle.location.x = paddle.location.x + paddle.SPEED;
		}
		paddle.setMovement(MOVE.STABLE);
	}

}
