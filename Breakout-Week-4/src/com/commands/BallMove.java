package com.commands;

import java.io.Serializable;

import com.entity.Ball;

public class BallMove implements Command, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Ball ball;
	private int prevX;
	private int prevY;
	private int prevVelX;
	private int prevVelY;
	
	public BallMove(Ball ball) {
		this.ball = ball;
	}

	@Override
	public void execute() {
		this.prevX = ball.getX();
		this.prevY = ball.getY();
		this.prevVelX = ball.getVelX();
		this.prevVelY = ball.getVelY();
		ball.setX(ball.getX() + ball.getVelX());
		ball.setY(ball.getY() + ball.getVelY());
	}

	@Override
	public void undo() {
		ball.setX(prevX);
		ball.setY(prevY);
		ball.setVelX(prevVelX);
		ball.setVelY(prevVelY);
	}

}
