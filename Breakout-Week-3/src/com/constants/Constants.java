package com.constants;

public interface Constants {

	// ----------- Breakout.java -------------------
	public static final int frameWidth = 900;
	public static final int frameHeight = 450;
	public static final int ballRadius = 40;

	public static final int velX = 1;
	public static final int velY = 1;
	public static final int initialBallXP = 0; /* ran.nextInt(100); */
	public static final int initialBallYP = 0; /* ran.nextInt(100); */
	public static final int fontSize = 40;
	public static final int initalPaddleXP = 250;
	public static final int initalPaddleYP = 350;
	// ----------- Breakout.java -------------------

	// ----------- Ball.java -------------------
	public static final int ballReflectXRightWall = 600;
	public static final int ballReflectXLeftWall = 0;
	public static final int ballReflectYTopWall = 0;
	public static final int ballReflectYBottomWall = 400;
	public static final int ballXPos = 50;
	public static final int ballYPos = 50;
	public static final int ballXSpeed = 1;
	public static final int ballYSpeed = 1;
	// ----------- Ball.java -------------------

	// ----------- Board.java -------------------
	public static final int BOARD_PANEL_WIDTH = 600;
	public static final int BOARD_PANEL_HEIGHT = 400;
	public static final int lineWidth = 600;
	public static final int lineHeight = 400;
	// ----------- Board.java -------------------

	// ----------- GridBagStrategy.java-------------------
	public static final int GRID_WIDTH = 175;
	public static final int GRID_HEIGHT = 250;
	// public static final int lineWidth = 600;
	// public static final int lineHeight = 400;
	// ----------- Board.java -------------------

	// ----------- BoxStrategy.java-------------------
	public static final int GRID_B_WIDTH = 175;
	public static final int GRID_B_HEIGHT = 400;
	public static final int GRID_BUTTON_HEIGHT = 50;
	// public static final int lineWidth = 600;
	// public static final int lineHeight = 400;
	// ----------- Board.java -------------------

	// ----------- Brick.java ------------------
	public static final int numBricks = 5;
	public static final int brickWidth = 50;
	public static final int brickHeight = 50;
	public static final int initialBrickXP = 300;
	public static final int initialBrickYP = 50;
	// ----------- Brick.java ------------------

	// ----------- Clock.java ------------------
	public static final int scheduleTime = 10;
	// ----------- Clock.java ------------------

	// ----------- Paddle.java ------------------
	public static final int reflectBallFromPaddle = 305;
	public static final int reflectPaddLeftWall = 0;
	public static final int reflectPaddRightWall = 530;
	public static final int paddleWidth = 80;
	public static final int paddleHeight = 10;
	public static final int SPEED = 30;
	// ----------- Paddle.java ------------------
}
