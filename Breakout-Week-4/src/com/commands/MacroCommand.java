
package com.commands;

import java.io.Serializable;
import java.util.ArrayList;

import com.entity.Ball;
import com.entity.Brick;
import com.entity.Clock;
import com.entity.Paddle;

public class MacroCommand implements Command, Serializable {

	private static final long serialVersionUID = 4L;
	public ArrayList<Command> commands;
	public Ball ball;
	public Paddle paddle;
	public Brick[] bricks;
	public Clock clock;

	public MacroCommand(Ball ball, Paddle paddle, Brick[] bricks, Clock clock) {
		this.commands = new ArrayList<>();
		this.ball = ball;
		this.paddle = paddle;
		this.bricks = bricks;
		this.clock = clock;
	}

	@Override
	public void execute() {
		for (Command c : commands) {
			c.execute();
		}
	}

	@Override
	public void undo() {
		for (Command c : commands) {
			c.undo();
		}
	}

	public void add(Command command) {
		commands.add(command);
	}
}
