package commands;

import entity.Ball;

public class BallMove implements Command {

	private Ball ball;

	public BallMove(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
		ball.location.x += ball.getVelX();
		ball.location.y += ball.getVelY();
	}

	@Override
	public void undo() {
		ball.location.x -= ball.getVelX();
		ball.location.y -= ball.getVelY();
	}

}
