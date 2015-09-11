import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Bird {
	private int height;
	private int width;
	private int x;
	private int y;
	private int acceleration;
	private int speed;
	private ImageIcon img;


	public Bird() {
		
		acceleration = 1;
		speed = -15;
		img = new ImageIcon("bird.png");
		height = img.getIconHeight();
		width = img.getIconWidth();
		x = 200;
		y = 300 - height/2;

	}

	public void jump() {
		speed = -15;
	}

	public void move() {
		speed += acceleration;
		y += speed;
	}

	public void paint(Graphics2D brush) {
		brush.drawImage(img.getImage(),x,y,null);
	}

	public Rectangle2D.Double getBounds() {
		return new Rectangle2D.Double(x,y, width,height);
	}

}