package fighting.characters;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fighting.MeleeFighter;

public class FighterKilley extends MeleeFighter {

	
	
	public FighterKilley(float x, float y, float z) {
		super(x, y, z);
		init();
	}
	
	public FighterKilley(float x, float y, float z, int decalBatchNo) {
		super(x, y, z, decalBatchNo);
		init();
	}

	public void init() {
		
		this.currTextureRegion = ImageCache.getFrame("killeyFightingIdle", 1);
		
		fightingIdleAnim = new GameAnimation(0.4f, new TextureRegion[]{
				ImageCache.getFrame("killeyFightingIdle", 1),
				
		});
		
		fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyFightingAttack", 1),
				ImageCache.getFrame("killeyFightingAttack", 2),
				ImageCache.getFrame("killeyFightingAttack", 3),
				ImageCache.getFrame("killeyFightingAttack", 4),
				ImageCache.getFrame("killeyFightingAttack", 5),
				ImageCache.getFrame("killeyFightingAttack", 6),
				ImageCache.getFrame("killeyFightingAttack", 6),
				ImageCache.getFrame("killeyFightingAttack", 4),
				ImageCache.getFrame("killeyFightingAttack", 3),
				ImageCache.getFrame("killeyFightingAttack", 2),
				ImageCache.getFrame("killeyFightingAttack", 1)
		});
		
		takingDamageAnim = new GameAnimation(1.0f, new TextureRegion[] {
				ImageCache.getFrame("killeyFightingAttack", 1),
				ImageCache.getFrame("killeyFightingAttack", 1)	
		});
		
		startJumpingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("killeyFightingIdle", 2),
				ImageCache.getFrame("killeyFightingAttack", 2),
				ImageCache.getFrame("killeyFightingAttack", 2)
		});
		
		jumpingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("killeyFightingAttack", 3)
		});
		
		attackingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("killeyFightingAttack", 4),
				ImageCache.getFrame("killeyFightingAttack", 5),
				ImageCache.getFrame("killeyFightingAttack", 6),
				ImageCache.getFrame("killeyFightingAttack", 7),
				ImageCache.getFrame("killeyFightingAttack", 8),
				ImageCache.getFrame("killeyFightingAttack", 9),
				ImageCache.getFrame("killeyFightingAttack", 10),
				ImageCache.getFrame("killeyFightingAttack", 10),
				ImageCache.getFrame("killeyFightingAttack", 11)
		});
		
		initializeListeners();
		
		this.chanceOfIdling = 0.006f;
	}
}