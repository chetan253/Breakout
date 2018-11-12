package entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import constants.Constants;

public class Paddle extends Component implements GraphicObject, Constants {
	private Component component;
	private static int paddleWidth = 80;
	private static int paddleHeight = 10;
	public final int SPEED;
	public MOVE move;
	
	public enum MOVE{
		LEFT, RIGHT, STABLE;
	}
	// Over here component can be either ball or brick if the brick drops and is
	// power brick
	// Moreover there can be multiple balls so we can make the 5th parameter as
	// Arraylist of components
	public Paddle(int x, int y, int speed, Component component) {
		super(x, y, paddleWidth, paddleHeight);
		this.SPEED = speed;
		this.setComponent(component);
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public void drawGraphic(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public void actionPerformedByGraphic() {
		if (this.getBounds().intersects(component.getBounds())) {
			if (component instanceof Ball)
				component.setVelY(component.getVelY() - 1);
		}
	}
	
	public void setMovement(MOVE move) {
		this.move = move;
	}
	
	public MOVE getMovement(){
		return this.move;
	}
	
	public void setX(int x) {
		this.location.setLocation(x, this.getY());
	}

	public void setY(int y) {
		this.location.setLocation(this.getX(), y);
	}
	public int getX() {
		return (int)location.getX();
	}

	public int getY() {
		return (int)location.getY();
	}

}
