package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entitiy {
    protected float x, y;
    protected int height, width;
    protected Rectangle2D.Float hitbox;
    public Entitiy(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void drawHitbox(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }
    /*protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }*/
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
