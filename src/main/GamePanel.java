package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {
	MouseInputs mouseInputs;

	private float xDelta = 100, yDelta = 100;
	private int playerDirection = -1;

	private BufferedImage img;
	private boolean moving = false;
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 18;
	private int playerAction = IDLE;

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
		animations = new BufferedImage[5][8];
		for (int j = 0; j < animations.length; j++) {
			for (int i = 0; i < animations[j].length; i++) {
				animations[j][i] = img.getSubimage(i * 80, j * 94, 80, 94);
			}
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


	private void setPanelSize() {
		Dimension size = new Dimension(1200, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}


	public void setDirection(int direction) {
		this.playerDirection = direction;
		moving = true;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private void updatePos() {
		if (moving) {
			switch (playerDirection) {
				case LEFT -> xDelta -= 2;
				case RIGHT -> xDelta += 2;
				case DOWN -> yDelta += 2;
				case UP -> yDelta -= 2;
			}
		}
	}

	private void setAnimation() {
		if (moving) playerAction = RUNNING;
		else playerAction = IDLE;
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= getSpriteAmount(playerAction)) {
				aniIndex = 0;
			}
		}
	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateAnimationTick();
		setAnimation();
		updatePos();
		g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 80, 94, null);
	}


	public void updateGame() {
		updateAnimationTick();
		setAnimation();
		updatePos();
	}
}
