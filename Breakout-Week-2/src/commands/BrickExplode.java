package commands;

import entity.Brick;

public class BrickExplode implements Command {

	Brick brick;

	public BrickExplode(Brick brick) {
		this.brick = brick;
	}

	@Override
	public void execute() {
		brick.setFlag(false);
	}

	@Override
	public void undo() {
		brick.setFlag(!brick.isFlag());
	}
}
