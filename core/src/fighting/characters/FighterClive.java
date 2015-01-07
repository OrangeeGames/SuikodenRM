package fighting.characters;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fighting.Fighter;

public class FighterClive extends Fighter {
	
	
	public FighterClive(float x, float y, float z) {
		super(x, y, z);
		init();
	}
	
	public FighterClive(float x, float y, float z, int decalBatchNo) {
		super(x, y, z, decalBatchNo);
		init();
	}
	
	
	private void init() {
		this.currTextureRegion = ImageCache.getFrame("cliveFightingIdle", 1);
		
		fightingIdleAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveFightingIdle", 1),
				ImageCache.getFrame("cliveFightingIdle", 2),
				ImageCache.getFrame("cliveFightingIdle", 3),
				ImageCache.getFrame("cliveFightingIdle", 4)
		});
		
		fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveFightingAttack", 1),
				ImageCache.getFrame("cliveFightingAttack", 2),
				ImageCache.getFrame("cliveFightingAttack", 3),
				ImageCache.getFrame("cliveFightingAttack", 4),
				ImageCache.getFrame("cliveFightingAttack", 5),
				ImageCache.getFrame("cliveFightingAttack", 6),
				ImageCache.getFrame("cliveFightingAttack", 7),
				ImageCache.getFrame("cliveFightingAttack", 8),
				ImageCache.getFrame("cliveFightingAttack", 6),
				ImageCache.getFrame("cliveFightingAttack", 5),
				ImageCache.getFrame("cliveFightingAttack", 4),
				ImageCache.getFrame("cliveFightingAttack", 3),
				ImageCache.getFrame("cliveFightingAttack", 2),
				ImageCache.getFrame("cliveFightingAttack", 1)
		});
		
		initializeListeners();
		
		this.chanceOfIdling = 0.01f;
	}
}
