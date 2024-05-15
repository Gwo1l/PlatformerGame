package bullets;

import entities.Player;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.LoadSave.BULLET_IMAGE;

public class Bullet {
    private Rectangle2D.Float hitbox;
    private int dir;
    private BufferedImage bulletImg = LoadSave.getSpriteAtlas(BULLET_IMAGE);
    private Player player;
    private boolean active;

    public Bullet(float x, float y, int dir) {
        hitbox = new Rectangle2D.Float(x, y, 6, 6);
        this.dir = dir;
    }

    public void updatePos() {
        hitbox.x += dir * (Game.SCALE);
    }

    public void draw(Graphics g, int xLvlOffset) {
        if (isActive())
            g.drawImage(bulletImg, (int) (hitbox.x - xLvlOffset), (int) (hitbox.y), null);
    }

    private void setPos(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public void drawHitbox(Graphics g, int lvlOffsetX){
        g.setColor(Color.red);
        g.drawRect((int)(hitbox.x - lvlOffsetX), (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
