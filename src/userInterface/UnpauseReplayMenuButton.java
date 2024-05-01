package userInterface;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.Constants.UserInterface.URMbuttons.*;
public class UnpauseReplayMenuButton extends PauseButton{
    BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private int rowIndex, index;
    public UnpauseReplayMenuButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);
        images = new BufferedImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
        }
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        else if (mousePressed) {
            index = 2;
        }
    }
    public void resetBooleans() {
        mousePressed = false;
        mouseOver = true;
    }
    public void draw(Graphics g) {
        g.drawImage(images[index], x, y, URM_SIZE, URM_SIZE, null);
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

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
