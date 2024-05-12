package main;

import entities.Player;
import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.Menu;
import levels.LevelManager;
import utilz.LoadSave;

import java.awt.*;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;

	private int playerYPosition = 160;
	private int playerXPosition = 500;
	private int playerWidth = 200;
	private int playerHeight = 200;

	private Playing playing;
	private Menu menu;

	public static final int TILES_DEFAULT_SIZE = 32;
	public static final float SCALE = 1.5f;
	public static final int TILES_IN_WIDTH = 26;
	public static final int TILES_IN_HEIGHT = 14;
	public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public static final int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF  += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}


			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

	}

	public void update() {
		switch (Gamestate.state) {
			case PLAYING -> {
				playing.update();
			}
			case OPTIONS -> {

			}
			case QUIT -> {
				System.exit(0);
			}
			case MENU -> {
				menu.update();
			}
			default -> System.exit(0);
		}
	}

	public void render(Graphics g) {
		switch (Gamestate.state) {
			case PLAYING -> {
				playing.draw(g);
			}
			case MENU -> {
				menu.draw(g);
			}
		}
	}



	public void windowFocusLost() {
		if (Gamestate.state == Gamestate.PLAYING) {
			playing.getPlayer().resetDirBoolean();
		}
	}

	public Menu getMenu() {
		return menu;
	}
	public Playing getPlaying() {
		return playing;
	}
}
