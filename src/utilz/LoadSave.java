package utilz;

import entities.Crabby;
import static utilz.Constants.EnemyConstants.CRABBY;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class LoadSave {
    public static final String PLAYER_SPRITE = "res/playerSprite.png";
    public static final String LEVEL_SPRITE = "res/level_sprites.png";
    public static final String MENU_BUTTONS = "res/button_atlas.png";
    public static final String BACKGROUND_OF_MENU = "res/menu_background.png";
    public static final String PAUSE_BACKGROUND = "res/pause_menu.png";
    public static final String SOUND_BUTTONS = "res/sound_button.png";
    public static final String URM_BUTTONS = "res/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "res/volume_buttons.png";
    public static final String ELDEN_RING_BACKGROUND = "res/elden_ring.jpg";
    public static final String PLAYING_BG_IMG = "res/playing_bg_img.png";
    public static final String BIG_CLOUDS = "res/big_clouds.png";
    public static final String SMALL_CLOUDS = "res/small_clouds.png";
    public static final String CRABBY_SPRITE = "res/crabby_sprite.png";
    public static final String STATUS_BAR = "res/health_power_bar.png";
    public static final String COMPLETED_IMAGE = "res/completed_sprite.png";
    public static final String BULLET_IMAGE = "res/ball.png";
    public static final String DEATH_SCREEN = "res/death_screen.png";

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

    public static BufferedImage[] getAllLevels(){
        URL url = LoadSave.class.getResource("/res/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for(int i = 0; i < filesSorted.length; i++) {
            for(int j = 0; j < files.length; j++) {
                if(files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[j] = files[j];
            }
        }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for(int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }
}
