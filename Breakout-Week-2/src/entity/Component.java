package entity;

import java.awt.Point;
import java.awt.Rectangle;

public abstract class Component {
	private Dimensions dimensions;
	public Point location;
	private int velX;
	private int velY;
	private int initX;
	private int initY;
	private int initVelX;
	private int initVelY;

	protected Component(int x, int y, int width, int height) {
		this.location = new Point(x, y);
		this.dimensions = new Dimensions(width, height);
		this.initX = x;
		this.initY = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getInitVelX() {
		return initVelX;
	}

	public void setInitVelX(int initVelX) {
		this.initVelX = initVelX;
		this.setVelX(initVelX);
	}

	public int getInitVelY() {
		return initVelY;
	}

	public void setInitVelY(int initVelY) {
		this.initVelY = initVelY;
		this.setVelY(initVelY);
	}

	public int getX() {
		return this.location.x;
	}

	public int getY() {
		return this.location.y;
	}

	public void setX(int x) {
		this.location.x = x;
	}

	public void setY(int y) {
		this.location.y = y;
	}

	public int getWidth() {
		return this.dimensions.getWidth();
	}

	public int getHeight() {
		return this.dimensions.getHeight();
	}

	public void reset() {
		this.setX(initX);
		this.setY(initY);
		this.setVelX(this.getInitVelX());
		this.setVelY(this.getInitVelY());
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

	class Dimensions {

		private int width;
		private int height;

		protected Dimensions(int width, int height) {
			this.width = width;
			this.height = height;
		}

		protected Dimensions(int radius) {
			this.width = radius;
		}

		protected int getWidth() {
			return this.width;
		}

		protected int getHeight() {
			return this.height;
		}
	}
}
