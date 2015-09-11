import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;

public class FlappyBird {


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel game = new GamePanel();
		frame.add(game);
		frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		Bird bird;
		Wall wall1;
		Wall wall2;
		Timer timer;
		Timer scoreTimer;
		boolean isDead;
		int score;
		ImageIcon background;
		Rectangle2D.Double ground;
		
		public GamePanel() {
			addKeyListener(new KeyAdapter());
			setFocusable(true);

			bird = new Bird();
			wall1 = new Wall(600);
			wall2 = new Wall(1000);

			timer = new Timer(30, new GameMotion());
			timer.start();

			//scoreTimer = new Timer(2400, new ScoreCounter());
			//scoreTimer.start();

			score = 0;
			isDead = false;

			background = new ImageIcon("background.png");
			ground = new Rectangle2D.Double(0,500,800,100);
		}

		private class KeyAdapter implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				
				int key = evt.getKeyCode();
				
				if (key == KeyEvent.VK_SPACE) {
					bird.jump();
				}
			}
			public void keyReleased(KeyEvent evt) {}
			public void keyTyped(KeyEvent evt) {}
		}

		private class GameMotion implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				wall1.move();
				wall2.move();
				bird.move();

				checkForHit();

				repaint();
			}
		}
/*
		private class ScoreCounter implements ActionListener {
			public void actionPerformed(ActionEvent evt) {
				score += 1;
			}
		}
*/
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;


				g2.drawImage(background.getImage(),0,0,null);
				wall1.paint(g2);
				wall2.paint(g2);

				g2.setColor(new Color(153,76,0));
				g2.draw(ground);
				g2.setColor(new Color(102,255,102));
				g2.fill(ground);
				g2.setColor(Color.BLACK);
				
				bird.paint(g2);
			
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Serif", Font.PLAIN, 20));
				g2.drawString("Score: " + score, 50,50);
			if (isDead) {
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Serif", Font.PLAIN, 30));
				g2.drawString("Game Over!", 300, 300);
				g2.drawString("Your final score was " + score, 250, 330);
				timer.stop();
				//scoreTimer.stop();
			}
		}

		public void checkForHit() {
			if (wall1.getTopBounds().intersects(bird.getBounds()) || wall2.getTopBounds().intersects(bird.getBounds())
				|| wall1.getBottomBounds().intersects(bird.getBounds()) || wall2.getBottomBounds().intersects(bird.getBounds()) 
				|| bird.getBounds().intersects(new Rectangle2D.Double(0,500,800,100))
				) {
				isDead = true;
			}
			if (bird.getBounds().intersects(wall1.getGapBounds()) && wall1.notPassed()) {
				wall1.setPassed(false);
				score++;
			}
			if (bird.getBounds().intersects(wall2.getGapBounds()) && wall2.notPassed()) {
				wall2.setPassed(false);
				score++;
			}
		}

	}
}