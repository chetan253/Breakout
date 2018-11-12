package commands;

import entity.Clock;

public class ClockTick implements Command {

	private Clock clock;

	public ClockTick(Clock clock) {
		this.clock = clock;
	}

	@Override
	public void execute() {
		clock.milisecondsElapsed += clock.DELTA;
	}

	@Override
	public void undo() {
		clock.milisecondsElapsed -= clock.DELTA;
	}

}
