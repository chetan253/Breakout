package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import com.constants.Constants;

public class Ball extends Component implements Constants, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;

	public Ball(int x, int y) {
		super(x, y);
		this.setInitVelX(velX);
		this.setInitVelY(velY);
	}

	public int getWidth() {
		return ballRadius;
	}

	public int getHeight() {
		return ballRadius;
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
		g.fillOval(this.getX(), this.getY(), ballRadius, ballRadius);
	}
	public int getBottomYCoordinates() {
		return this.getY() + this.getHeight();
	}

	public int getRightXCoordinates() {
		return this.getX() + this.getWidth();
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
}
