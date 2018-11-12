package com.commands;

import java.io.Serializable;


import com.entity.Brick;

public class BrickExplode implements Command, Serializable {
	


	private static final long serialVersionUID = 2L;
	Brick brick;

	public BrickExplode(Brick brick) {
		this.brick = brick;
	}

	@Override
	public void execute() {
	
		if(brick != null)
			brick.setFlag(false);
	}

	@Override
	public void undo() {
		if(brick != null)
			brick.setFlag(!brick.isFlag());
	}
}
