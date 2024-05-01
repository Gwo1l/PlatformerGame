package userInterface;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import static utils.Constants.UserInterface.PauseButtons.*;
import static utils.Constants.UserInterface.URMbuttons.*;
import static utils.Constants.UserInterface.VolumeButtons.*;
public class PauseOverlay {
    private BufferedImage backgroundImage;
    private int bgX, bgY, bgW, bgH;
    private int soundX = (int)(450 * Game.SCALE);
    private int musicY = (int)(140 * Game.SCALE);
    private int sfxY = (int)(186 * Game.SCALE);
    private int dialogueY = (int)(232 * Game.SCALE);
    private UnpauseReplayMenuButton menuB, replayB, unpauseB;
    private VolumeButton volumeButton;
    private SoundButton musicButton, soundEffectsButton, dialogueButton;
    private Playing playing;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUnpauseReplayMenuButtons();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int volumeX = (int)(309 * Game.SCALE);
        int volumeY = (int)(278 * Game.SCALE);
        volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUnpauseReplayMenuButtons() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);
        int buttonY = (int) (325 * Game.SCALE);
        menuB = new UnpauseReplayMenuButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
        replayB = new UnpauseReplayMenuButton(replayX, buttonY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UnpauseReplayMenuButton(unpauseX, buttonY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        soundEffectsButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
        //dialogueButton = new SoundButton(soundX, dialogueY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImage = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        bgW = (int) (backgroundImage.getWidth() * Game.SCALE);
        bgH = (int) (backgroundImage.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int)(25 * Game.SCALE);
    }

    public void update() {
        musicButton.update();
        soundEffectsButton.update();
        menuB.update();
        unpauseB.update();
        replayB.update();
        volumeButton.update();
    }
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, bgX, bgY, bgW, bgH, null);
        musicButton.draw(g);
        soundEffectsButton.draw(g);

        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }
    }

    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
        if (isIn(e, musicButton)) {
            musicButton.setMousePressed(true);
        }
        else if (isIn(e, soundEffectsButton)) {
            soundEffectsButton.setMousePressed(true);
        }
        else if (isIn(e, unpauseB)) {
            unpauseB.setMousePressed(true);
        }
        else if (isIn(e, replayB)) {
            replayB.setMousePressed(true);
        }
        else if (isIn(e, menuB)) {
            menuB.setMousePressed(true);
        }
        else if (isIn(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }


    public void mouseReleased(MouseEvent e) {
        if (isIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        }
        else if (isIn(e, soundEffectsButton)) {
            if (soundEffectsButton.isMousePressed()) {
                soundEffectsButton.setMuted(!soundEffectsButton.isMuted());
            }
        }
        else if (isIn(e, soundEffectsButton)) {
            if (soundEffectsButton.isMousePressed()) {
                soundEffectsButton.setMuted(!soundEffectsButton.isMuted());
            }
        }
        else if (isIn(e, menuB)) {
            if (menuB.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        }
        else if (isIn(e, replayB)) {
            if (replayB.isMousePressed()) {
                System.out.println("replay lvl!");
            }
        }
        else if (isIn(e, unpauseB)) {
            if (unpauseB.isMousePressed()) {
                playing.unpauseGame();
                //Gamestate.state = Gamestate.PLAYING;
            }
        }

        musicButton.resetBooleans();
        soundEffectsButton.resetBooleans();
        menuB.resetBooleans();
        replayB.resetBooleans();
        unpauseB.resetBooleans();
        volumeButton.resetBooleans();
    }


    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        soundEffectsButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e, musicButton)) {
            musicButton.setMouseOver(true);
        }
        else if (isIn(e, soundEffectsButton)) {
            soundEffectsButton.setMouseOver(true);
        }
        else if (isIn(e, menuB)) {
            menuB.setMouseOver(true);
        }
        else if (isIn(e, replayB)) {
            replayB.setMouseOver(true);
        }
        else if (isIn(e, unpauseB)) {
            unpauseB.setMouseOver(true);
        }
        else if (isIn(e, volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e, PauseButton button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }
}
