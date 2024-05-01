package gamestates;
import entities.Player;
import levels.LevelManager;
import main.Game;
import userInterface.PauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private int playerYPosition = 160;
    private int playerXPosition = 500;
    private int playerWidth = 200;
    private int playerHeight = 200;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private Player player;
    private boolean paused = false;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(playerXPosition, playerYPosition, playerWidth, playerHeight);
        player.loadlvlData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay = new PauseOverlay(this);
    }
    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBoolean();
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
        }
        else {
            pauseOverlay.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
        if (paused) {
            pauseOverlay.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseClicked(e);
        }
    }
    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }
    public void unpauseGame() {
        paused = false;
    }
}
