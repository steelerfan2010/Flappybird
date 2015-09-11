import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Wall {

	private int xPos;
	private int yPos;
	private int speed;
	private int width;
	private int height;
	private int gap;
	private Rectangle2D.Double wallBottom;
	private Rectangle2D.Double wallTop;
	private Rectangle2D.Double wallGap;
	private boolean notPassed;

	public Wall(int x) {
		xPos = x;
		yPos = (int)(Math.random()*400) + 100;
		speed = -5;
		width = 45;
		height = 500;
		gap = 180;
		notPassed = true;

		wallBottom = new Rectangle2D.Double(xPos, yPos, width, height);
		wallTop = new Rectangle2D.Double(xPos, (yPos - gap - height), width, height);
		wallGap = new Rectangle2D.Double(xPos, (yPos - gap), 1, gap);
	}

	public void paint(Graphics2D brush) {
		brush.draw(wallBottom);
		brush.draw(wallTop);
		brush.setColor(Color.GREEN);
		brush.fill(wallBottom);
		brush.fill(wallTop);
	}

	public void move() {
		xPos += speed;

		if (xPos <= 0) {
			xPos = 800;
			yPos = (int)(Math.random()*400) + 100;
			notPassed = true;
		}

		wallBottom.setRect(xPos, yPos, width, height);
		wallTop.setRect(xPos, (yPos - gap - height), width, height);
		wallGap.setRect(xPos, (yPos - gap), 1, gap);
	}

	public void setPassed(boolean value) {
		notPassed = value;
	}

	public boolean notPassed() {
		return notPassed;
	}

	public Rectangle2D.Double getBottomBounds() {
		return wallBottom;
	}

	public Rectangle2D.Double getTopBounds() {
		return wallTop;
	}

	public Rectangle2D.Double getGapBounds() {
		return wallGap;
	}

}