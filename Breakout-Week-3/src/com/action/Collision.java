package com.action;

import javax.swing.JOptionPane;

import com.commands.Direction;
import com.constants.Constants;
import com.entity.Ball;
import com.entity.Brick;
import com.entity.Component;
import com.entity.Paddle;
import com.sounds.Sounds;

public class Collision implements Constants {

	private enum Side {
		UP, DOWN, LEFT, RIGHT;
	}

	private Ball ball;
	private Paddle paddle;

	public Collision(Ball ball, Paddle paddle) {
		this.ball = ball;
		this.paddle = paddle;
	}

	public Direction ballToWall() {
		int currentBallPosX = ball.getX();
		int currentBallPosY = ball.getY();
		int leftWallPosition = ballReflectXLeftWall;
		int rightWallPosition = ballReflectXRightWall;
		int topWallPosition = ballReflectYTopWall;

		// check for hit on the wall
		boolean hitLeftWall = currentBallPosX + ball.getVelX() < leftWallPosition;
		boolean hitRightWall = currentBallPosX +  ball.getWidth()+ ball.getVelX() > rightWallPosition;
		boolean hitTopWall = currentBallPosY + ball.getVelY() < topWallPosition;
		boolean hitBottomWall = currentBallPosY +ball.getHeight() + ball.getVelY() > paddle.getY() + paddle.getHeight();
		
		// if ball hits one of the horizontal sides of wall
		if (hitLeftWall || hitRightWall) {
			return Direction.X;
		}
		// if ball hits one of the vertical sides of wall
		if (hitTopWall) {
			return Direction.Y;
		}

		if (hitBottomWall) {
			Sounds.LOSE.play();
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}
		return Direction.NONE;
	}

	public Direction ballToPaddle() {
		if (paddle.getBounds().intersects(ball.getBounds())) {
			return Direction.Y;
		}
		return Direction.NONE;
	}

	public Direction balltoBrick(Brick brick, int x, int y) {
		if (brick.getBounds().intersects(ball.getBounds()) && brick.isFlag()) {
			Sounds.BALL_BRICK.play();
			return checkCollisionWith(brick, x, y);
		}
		return Direction.NONE;
	}

	public Direction checkCollisionWith(Brick element, int prevOffsetX, int prevOffsetY) {

		// right down to left up
		if ((ball.getX() <= ball.getX() - prevOffsetX && ball.getY() >= ball.getY() - prevOffsetY)) {
			// right down to right down - hits right side of element
			if (ball.getYRadiusCoordinates() > element.getY()
					&& ball.getXRadiusCoordinates() > element.getRightXCoordinates()) {
				return Direction.X;
			}
			return Direction.Y;
		}
		// left down to right up
		else if (ball.getX() >= ball.getX() - prevOffsetX && ball.getY() >= ball.getY() - prevOffsetY) {
			// left down to left down - hits left side of element
			if (ball.getYRadiusCoordinates() > element.getY() && ball.getXRadiusCoordinates() < element.getX()) {
				return Direction.X;
			}
			return Direction.Y;
		}
		// left up to right down
		else if (ball.getX() >= ball.getX() - prevOffsetX && ball.getY() <= ball.getY() - prevOffsetY) {
			// left up to left up - hits left side of element
			if (ball.getYRadiusCoordinates() < element.getBottomYCoordinates()
					&& ball.getXRadiusCoordinates() < element.getX()) {
				return Direction.X;
			}
			return Direction.Y;
		}
		// right up to left down
		else if (ball.getX() <= ball.getX() - prevOffsetX && ball.getY() <= ball.getY() - prevOffsetY) {
			// right up to right up - hits right side of element
			if (ball.getYRadiusCoordinates() < element.getBottomYCoordinates()
					&& ball.getXRadiusCoordinates() > element.getRightXCoordinates()) {
				return Direction.X;
			}
			return Direction.Y;
		}
		return Direction.NONE;
	}

}
