package com.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import com.action.Breakout;
import com.action.Collision;
import com.commands.BallMove;
import com.commands.BrickExplode;
import com.commands.ClockTick;
import com.commands.Direction;
import com.commands.MacroCommand;
import com.commands.PaddleMove;
import com.entity.Ball;
import com.entity.Brick;
import com.entity.Clock;
import com.entity.Paddle;
import com.observer.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameController implements Observer {

	private Ball ball;
	private Paddle paddle;
	private Collision collision;
	private ArrayList<MacroCommand> commands;
	private Breakout game;
	private boolean gameTimer = true;
	private Clock clock;
	private int undoCtr;
	private Brick[] bricks;
	private MacroCommand macroCommand;
	public SwingWorker<Boolean, MacroCommand> worker;
	public static Logger logger = LogManager.getLogger(GameController.class);

	public GameController(Ball ball, Paddle paddle, Brick[] bricks, Clock clock, Breakout game) {
		this.commands = new ArrayList<>();
		this.ball = ball;
		this.paddle = paddle;
		this.game = game;
		this.collision = new Collision(ball, paddle);
		this.undoCtr = 0;
		this.bricks = bricks;
		this.clock = clock;
	}

	@Override
	public void update() {
		handleCommandList();

		Direction direction = collision.ballToWall();
		if (direction == Direction.X) {
			ball.setVelX(-1 * ball.getVelX());
		} else if (direction == Direction.Y) {
			ball.setVelY(-1 * ball.getVelY());
		}

		direction = collision.ballToPaddle();
		if (direction == Direction.Y) {
			ball.setVelY(-1 * ball.getVelY());
		}

		Brick collidedBrick = null;
		for (Brick brick : bricks) {
			direction = collision.balltoBrick(brick, ball.getVelX(), ball.getVelY());
			if (direction == Direction.X) {
				ball.setVelX(-1 * ball.getVelX());
			} else if (direction == Direction.Y) {
				ball.setVelY(-1 * ball.getVelY());
			}
			if (direction != Direction.NONE) {
				collidedBrick = brick;
				break;
			}
		}
		BallMove ballCommand = new BallMove(ball);
		ClockTick ticker = new ClockTick(clock);
		PaddleMove paddleCommand = new PaddleMove(paddle);
		BrickExplode brickCommand = new BrickExplode(collidedBrick);
		MacroCommand macroCommand = new MacroCommand(ball, paddle, bricks, clock);
		macroCommand.add(paddleCommand);
		// Add all commands in macro command
		macroCommand.add(ticker);
		macroCommand.add(ballCommand);
		macroCommand.add(brickCommand);
		// Execute all Commands at once
		macroCommand.execute();
//		System.out.println("GameController Ball Saved x" + ball.getX() + " y " + ball.getY());
		commands.add(macroCommand);
	}

	public ArrayList<MacroCommand> getMacroCommandList() {
		return this.commands;
	}

	public void setMacroCommandList(ArrayList<MacroCommand> list) {
		
		this.commands.clear();
		this.commands = list;
		logger.debug("List has been initialized for macro commands list");
	}
	
	public void handleCommandList() {
		while (undoCtr != 0 && commands.size() > 1) {
			commands.remove(commands.size() - 1);
			undoCtr--;
		}
	}

	public void undo() {
		int curPoint = commands.size() - 1 - undoCtr;
		if (curPoint <= 0)
			return;
		MacroCommand macroCommand = commands.get(curPoint);
		macroCommand.undo();
		undoCtr++;
		game.update();
	}

	public void replay() throws InvocationTargetException, InterruptedException {
		game.update();
		worker = new SwingWorker<Boolean, MacroCommand>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				for (MacroCommand macroCommand : commands) {
					macroCommand.undo();
					game.update();
					Thread.sleep(10);
				}
				return true;
			}
		};
		worker.execute();
		logger.fatal("Replay initiated");
	}
	
	public void clearMacroCommandlist()
	{
		commands.removeAll(commands);
	}
}
