package entities;

import main.Game;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.HelpMethods.*;
import static utils.Constants.Directions.*;
import static utils.Constants.Directions.UP;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entitiy {
    private BufferedImage[][] animations;
    private boolean moving = false;
    private int aniTick, aniIndex, aniSpeed = 18;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean left, right, up, down, jump;
    private int spriteWidth = 80;
    private int spriteHeight = 94;
    private float playerSpeed = 2.0f;
    private float xHitboxOffset = 9 * Game.SCALE;
    private float yHitboxOffset = 8 * Game.SCALE;
    private int[][] lvlData;

    private float airSpeed = 0f;
    private float gravitiy = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 17 * Game.SCALE, 28 * Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xHitboxOffset), (int)(hitbox.y - yHitboxOffset), 53, 63, null);
        //drawHitbox(g);
    }


    private void updatePos() {
        moving = false;

        if (jump)
            jump();

        if (!left && !right && !inAir) return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;

        if (!inAir) {
            if (!areWeOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (canIMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.height, hitbox.width, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravitiy;
                updateXPos(xSpeed);
            }
            else {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                }
                else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        }
        else {
            updateXPos(xSpeed);
        }
        moving = true;


        /*if (canIMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.height, hitbox.width, lvlData)) {
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }*/
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canIMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.height, hitbox.width, lvlData)) {
            hitbox.x += xSpeed;
        }
        else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        /*if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            }
            else {
                playerAction = FALLING;
            }
        }*/

        if (jump) playerAction = JUMP;

        if (startAnimation != playerAction)
            resetAnimationTick();
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
                jump = false;
            }
        }
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_SPRITE);
        animations = new BufferedImage[5][8];
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }

    public void loadlvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!areWeOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
    }

    public void setJump(boolean jump) {
        this.jump = jump;
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
