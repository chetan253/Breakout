package com.commands;

import java.io.Serializable;

import com.constants.Constants;
import com.entity.Paddle;
import com.entity.Paddle.MOVE;

public class PaddleMove implements Command, Constants, Serializable {

	private static final long serialVersionUID = 5L;
	Paddle paddle;
	private int prevX;
	private int prevY;
	public PaddleMove(Paddle paddle) {
		this.paddle = paddle;
	}

	@Override
	public void execute() {
		this.prevX = paddle.getX();
		this.prevY = paddle.getY();
		if (paddle.getX() >= reflectPaddLeftWall && paddle.getMovement() == MOVE.LEFT) {
			paddle.location.x -= SPEED;
		}
		else if (paddle.getX() >= reflectPaddLeftWall && paddle.getMovement() == MOVE.RIGHT) {
			paddle.location.x += SPEED;
		}
		paddle.setMovement(MOVE.STABLE);
	}

	@Override
	public void undo() {
		paddle.setX(prevX);
		paddle.setY(prevY);
	}

}
