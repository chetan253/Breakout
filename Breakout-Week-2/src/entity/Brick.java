package entity;

import java.awt.Color;
import java.awt.Graphics;

import constants.Constants;

public class Brick extends Component implements GraphicObject, Constants {
	private static int brickWidth = 50;
	private static int brickHeight = 50;
	private boolean flag;

	public Brick(int x, int y) {
		super(x, y, brickWidth, brickHeight);
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

	public void actionPerformedByGraphic() {
		// insert code here
	}
}
