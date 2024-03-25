package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entitiy {
    private BufferedImage[][] animations;
    private boolean moving = false, jumping = false;
    private int aniTick, aniIndex, aniSpeed = 18;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean left, right, up, down;
    private float playerSpeed = 2.0f;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 80, 94, null);
    }


    private void updatePos() {
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        }
        else if (!left && right) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        }
        else if (!up && down) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving) playerAction = RUNNING;
        else playerAction = IDLE;

        if (jumping) playerAction = JUMP;

        if (startAnimation != playerAction) resetAnimationTick();
    }

    private void resetAnimationTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)) {
                aniIndex = 0;
                jumping = false;
            }
        }
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/playerSprite.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[5][8];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 80, j * 94, 80, 94);
                }
            }
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

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBoolean() {
        right = false;
        down = false;
        up = false;
        left = false;
    }
}
