package com.entity;

import java.awt.Point;
import java.io.Serializable;

public abstract class Component implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9L;
	public Point location;
	private int velX;
	private int velY;
	private int initX;
	private int initY;
	private int initVelX;
	private int initVelY;

	protected Component(int x, int y) {
		this.location = new Point(x, y);
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

	public void reset() {
		this.setX(initX);
		this.setY(initY);
		this.setVelX(this.getInitVelX());
		this.setVelY(this.getInitVelY());
	}
}
