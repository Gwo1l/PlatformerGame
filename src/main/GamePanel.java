package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	MouseInputs mouseInputs;

	private float xDelta = 100, yDelta = 100;

	private BufferedImage img;
	private BufferedImage[] idleAni;

	public GamePanel() {
		setPanelSize();
		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void loadAnimations() {
		idleAni = new BufferedImage[4];
		for (int i =0; i < idleAni.length; i++) {
			idleAni[i] = img.getSubimage(i * 200, 0, 200, 200);
		}
	}

	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/playerSprite.png");
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1200, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}




	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(idleAni[2], (int) xDelta, (int) yDelta, 150, 200, null);
	}

}
