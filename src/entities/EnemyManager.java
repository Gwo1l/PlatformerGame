package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import bullets.Bullet;
import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.isBulletHittingLevel;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crabby> crabbies = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		crabbies = level.getCrbs();
	}

	public void update(int[][] lvlData, Player player) {
		boolean isAnyActive = false;
		for (Crabby c : crabbies)
			if (c.isActive()) {
				c.update(lvlData, player);
				isAnyActive = true;
			}
		if (!isAnyActive) {
			playing.setLevelCompleted(true);
		}
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		for (Crabby c : crabbies) {
			if (c.isActive()) {
				g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
						(int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y, CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
			}
		}
	}

	public void checkEnemyHit(Bullet bullet, int[][]lvlData) {
		for(Crabby c: crabbies)
			if(c.isActive()&&c.enemyState!=DEAD)
				if(bullet.isActive()) {
					if(bullet.getHitbox().intersects(c.getHitbox())) {
						c.hurt(10);
						bullet.setActive(false);
					}else if(isBulletHittingLevel(bullet, lvlData))
						bullet.setActive(false);
				}
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
	}

	public void resetAllEnemies(){
		for(Crabby c: crabbies)
			c.resetEnemy();
	}

	public ArrayList<Crabby> getCrabbies() {
		return crabbies;
	}

	public void setCrabbies(ArrayList<Crabby> crabbies) {
		this.crabbies = crabbies;
	}

}
