package com.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import com.constants.Constants;

public class Paddle extends Component implements Constants,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	public MOVE move;

	public enum MOVE {
		LEFT, RIGHT, STABLE;
	}

	// Over here component can be either ball or brick if the brick drops and is
	// power brick
	// Moreover there can be multiple balls so we can make the 5th parameter as
	// Arraylist of components
	public Paddle(int x, int y) {
		super(x, y);
	}
	
	public void drawGraphic(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public int getWidth() {
		return paddleWidth;
	}

	public int getHeight() {
		return paddleHeight;
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

	public void setMovement(MOVE move) {
		this.move = move;
	}

	public MOVE getMovement() {
		return this.move;
	}

	public void setX(int x) {
		this.location.setLocation(x, this.getY());
	}

	public void setY(int y) {
		this.location.setLocation(this.getX(), y);
	}

	public int getX() {
		return (int) location.getX();
	}

	public int getY() {
		return (int) location.getY();
	}

}
