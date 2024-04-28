package userInterface;

import gamestates.Gamestate;
import utils.LoadSave;
import static utils.Constants.UserInterface.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonInMenu {
    private int xPos;
    private int yPos;
    private int rowIndex;
    private int index;
    private boolean mouseOver, mousePressed;
    private int xOffsetCenter = BUTT0N_WIDTH / 2;
    private Rectangle bounds;
    private Gamestate state;
    private BufferedImage[] images;
    public ButtonInMenu(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;

        loadImages();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, BUTT0N_WIDTH, BUTT0N_HEIGHT);
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * BUTT0N_WIDTH_DEFUALT, rowIndex * BUTT0N_HEIGHT_DEFUALT, BUTT0N_WIDTH_DEFUALT, BUTT0N_HEIGHT_DEFUALT);
        }
    }
    public void draw(Graphics g) {
        g.drawImage(images[index], xPos - xOffsetCenter, yPos, BUTT0N_WIDTH, BUTT0N_HEIGHT, null);
    }
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void applyGamestate() {
        Gamestate.state = state;
    }
    public void resetBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
}
