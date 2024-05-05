package utilz;

import main.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {
    public static boolean canIMoveHere(float x, float y, float height, float width, int[][] lvlData) {
        if (!isThatWall(x, y, lvlData))
            if (!isThatWall(x + width, y, lvlData))
                if (!isThatWall(x, y + height, lvlData))
                    if (!isThatWall(x + width, y + height, lvlData))
                        return true;
        return false;
    }

    public static boolean isThatWall(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if (x < 0 || x >= maxWidth || y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        int positionTile = lvlData[(int) yIndex][(int) xIndex];
        if (positionTile >= 48 || positionTile < 0 || positionTile != 11)
            return true;
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        }
        else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        }
        else {
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean areWeOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        if (!isThatWall(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!isThatWall(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false;
        return true;
    }
    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        return isThatWall(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }


}