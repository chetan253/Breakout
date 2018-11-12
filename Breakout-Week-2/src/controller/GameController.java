package controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingWorker;
import action.Breakout;
import action.Collision;
import commands.BallChangeXVel;
import commands.BallChangeYVel;
import commands.BallMove;
import commands.BrickExplode;
import commands.MacroCommand;
import commands.PaddleMoveLeft;
import commands.PaddleMoveRight;
import entity.Ball;
import entity.Brick;
import entity.Paddle;
import entity.Paddle.MOVE;
import observer.Observer;
import commands.ClockTick;
import commands.Direction;
import entity.Clock;

public class GameController implements Observer {

	private Ball ball;
	private Paddle paddle;
	private Collision collision;
	public ArrayList<MacroCommand> commands;
	private Breakout game;
	private boolean gameTimer = true;
	private Clock clock;
	private int undoCtr;
	private Brick[] bricks;
	private MacroCommand macroCommand;

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
		while (undoCtr != 0) {
			commands.remove(commands.size() - 1);
			undoCtr--;
		}

		MacroCommand macroCommand = new MacroCommand();

		Direction direction = collision.ballToWall();
		if (direction == Direction.X) {
			macroCommand.add(new BallChangeXVel(ball));
		} else if (direction == Direction.Y) {
			macroCommand.add(new BallChangeYVel(ball));
		}

		direction = collision.ballToPaddle();
		if (direction == Direction.Y) {
			macroCommand.add(new BallChangeYVel(ball));
		}

		for (Brick brick : bricks) {
			direction = collision.balltoBrick(brick, ball.getVelX(), ball.getVelY());
			if (direction == Direction.X) {
				macroCommand.add(new BallChangeXVel(ball));
				macroCommand.add(new BrickExplode(brick));
			} else if (direction == Direction.Y) {
				macroCommand.add(new BallChangeYVel(ball));
				macroCommand.add(new BrickExplode(brick));
			}
		}
		
		if(paddle.getMovement() == MOVE.LEFT) {
			macroCommand.add(new PaddleMoveLeft(paddle));
		}
		else if(paddle.getMovement() == MOVE.RIGHT) {
			macroCommand.add(new PaddleMoveRight(paddle));
		}

		BallMove ballCommand = new BallMove(ball);
		ClockTick ticker = new ClockTick(clock);
		// Add all commands in macro command
		macroCommand.add(ticker);
		macroCommand.add(ballCommand);

		// Execute all Commands at once
		macroCommand.execute();
		commands.add(macroCommand);
	}

	public ArrayList<MacroCommand> getMacroCommandList() {
		return this.commands;
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
		game.initialize();
		game.update();
		Iterator<MacroCommand> iter = commands.iterator();
		SwingWorker<Boolean, MacroCommand> worker = new SwingWorker<Boolean, MacroCommand>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				while (iter.hasNext()) {
					MacroCommand macroCommand = iter.next();
					macroCommand.execute();
					game.update();
					Thread.sleep(10);
				}
				return true;
			}
		};
		worker.execute();
	}
}
