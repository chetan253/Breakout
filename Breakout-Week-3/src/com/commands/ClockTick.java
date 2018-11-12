package com.commands;

import java.io.Serializable;

import com.entity.Clock;

public class ClockTick implements Command, Serializable {

	private static final long serialVersionUID = 3L;
	private Clock clock;
	private long pastTime;
	
	public ClockTick(Clock clock) {
		this.clock = clock;
	}

	@Override
	public void execute() {
		this.pastTime = clock.milisecondsElapsed;
		clock.milisecondsElapsed += clock.DELTA;
	}

	@Override
	public void undo() {
		clock.setTime(pastTime);
	}

}
