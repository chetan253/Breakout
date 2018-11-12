package com.action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.commands.MacroCommand;
import com.controller.GameController;
import com.entity.Paddle;
import com.entity.Paddle.MOVE;

public class PaddleListener implements KeyListener {

	private Paddle paddle;
	private GameController controller;

	public PaddleListener(Paddle paddle, GameController controller) {
		this.paddle = paddle;
		this.controller = controller;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		ArrayList<MacroCommand> commandList = controller.getMacroCommandList();
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if (paddle.getX() > 20) {
				paddle.setMovement(MOVE.LEFT);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if (paddle.getX() < 500) {
				paddle.setMovement(MOVE.RIGHT);
			}
		}
//		if (e.getKeyCode() == KeyEvent.VK_Z) {
//			controller.undo();
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
