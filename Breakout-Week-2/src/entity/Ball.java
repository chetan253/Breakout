package entity;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import constants.Constants;

public class Ball extends Component implements Constants {

	public Ball(int x, int y) {
		super(x, y, ballRadius, ballRadius);
		this.setInitVelX(velX);
		this.setInitVelY(velY);
	}

	public int getBottomCoordinates() {
		return getY() + getHeight();
	}

	public int getRightCoordinates() {
		return getX() + getWidth();
	}

	public int getYRadiusCoordinates() {
		return getY() + (getHeight() / 2);
	}

	public int getXRadiusCoordinates() {
		return getX() + (getWidth() / 2);
	}

	public void drawGraphic(Graphics g) {
		g.fillOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

}
