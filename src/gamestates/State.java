package gamestates;

import main.Game;
import userInterface.ButtonInMenu;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;
    public boolean isIn(MouseEvent e, ButtonInMenu buttonInMenu) {
        return buttonInMenu.getBounds().contains(e.getX(), e.getY());
    }
    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
