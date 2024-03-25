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
	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		setPanelSize();
		mouseInputs = new MouseInputs(this);
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}



	private void setPanelSize() {
		Dimension size = new Dimension(1200, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}

	public Game getGame() {
		return game;
	}


}
