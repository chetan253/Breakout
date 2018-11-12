package commands;

import entity.Ball;

public class BallChangeYVel implements Command {
	private Ball ball;

	public BallChangeYVel(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
		ball.setVelY(-1 * ball.getVelY());
	}

	@Override
	public void undo() {
		ball.setVelY(-1 * ball.getVelY());
	}
}
