package com.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.constants.Constants;

public class Brick extends Component implements GraphicObject, Constants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	private boolean flag;

	public Brick(int x, int y) {
		super(x, y);
		this.flag = true;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void drawGraphic(Graphics g) {
		if (flag == true) {
			g.setColor(Color.red);
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
	}
	public int getBottomYCoordinates() {
		return this.getY() + this.getHeight();
	}

	public int getWidth() {
		return brickWidth;
	}

	public int getHeight() {
		return brickHeight;
	}
	
	public int getRightXCoordinates() {
		return this.getX() + this.getWidth();
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	public void actionPerformedByGraphic() {
		// insert code here
	}
}
