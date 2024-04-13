package utils;

import main.Game;

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
        if (x < 0 || x >= Game.GAME_WIDTH || y < 0 || y >= Game.GAME_HEIGHT)
            return true;
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;
        int positionTile = lvlData[(int) yIndex][(int) xIndex];
        if (positionTile >= 48 || positionTile < 0 || positionTile != 11)
            return true;
        return false;
    }
}
