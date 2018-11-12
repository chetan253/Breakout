package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import commands.MacroCommand;
import commands.PaddleMoveLeft;
import commands.PaddleMoveRight;
import controller.GameController;
import entity.Paddle;
import entity.Paddle.MOVE;

public class PaddleListener implements KeyListener {

	private Paddle paddle;
	private GameController controller;
	private PaddleMoveLeft moveLeft;
	private PaddleMoveRight moveRight;

	public PaddleListener(Paddle paddle, GameController controller) {
		this.paddle = paddle;
		this.controller = controller;
		this.moveLeft = new PaddleMoveLeft(this.paddle);
		this.moveRight = new PaddleMoveRight(this.paddle);
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
//				moveLeft.execute();
//				commandList.get(commandList.size() - 1).add(moveLeft);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if (paddle.getX() < 500) {
				paddle.setMovement(MOVE.RIGHT);
//				moveRight.execute();
//				commandList.get(commandList.size() - 1).add(moveRight);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			controller.undo();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
