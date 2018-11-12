package commands;

import entity.Ball;

public class BallChangeXVel implements Command {
	private Ball ball;

	public BallChangeXVel(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
		ball.setVelX(-1 * ball.getVelX());
	}

	@Override
	public void undo() {
		ball.setVelX(-1 * ball.getVelX());
	}

}
