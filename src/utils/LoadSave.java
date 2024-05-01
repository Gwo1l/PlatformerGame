package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_SPRITE = "playerSprite.png";
    public static final String LEVEL_SPRITE = "level_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String BACKGROUND_OF_MENU = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
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
        return img;
    }

    public static int[][] getLevelData() {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_ONE_DATA);
        int height = image.getHeight();
        int width = image.getWidth();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Color color = new Color(image.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48)
                    value = 0;
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}
