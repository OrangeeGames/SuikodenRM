package fighting.characters;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fighting.MeleeFighter;

public class FighterHighlander extends MeleeFighter {

	
	
	public FighterHighlander(float x, float y, float z) {
		super(x, y, z);
		init();
	}
	
	public FighterHighlander(float x, float y, float z, int decalBatchNo) {
		super(x, y, z, decalBatchNo);
		init();
	}

	public void init() {
		
		this.currTextureRegion = ImageCache.getFrame("highlanderFightingIdle", 1);
		
		fightingIdleAnim = new GameAnimation(0.1f, new TextureRegion[]{
				ImageCache.getFrame("highlanderFightingIdle", 1),
				ImageCache.getFrame("highlanderFightingIdle", 2),
				ImageCache.getFrame("highlanderFightingIdle", 3),
				ImageCache.getFrame("highlanderFightingIdle", 2),
				ImageCache.getFrame("highlanderFightingIdle", 1),
				
		});
		
		fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("highlanderFightingAttack", 1),
				ImageCache.getFrame("highlanderFightingAttack", 2),
				ImageCache.getFrame("highlanderFightingAttack", 3),
				ImageCache.getFrame("highlanderFightingAttack", 4),
				ImageCache.getFrame("highlanderFightingAttack", 5),
				ImageCache.getFrame("highlanderFightingAttack", 6),
				ImageCache.getFrame("highlanderFightingAttack", 6),
				ImageCache.getFrame("highlanderFightingAttack", 4),
				ImageCache.getFrame("highlanderFightingAttack", 3),
				ImageCache.getFrame("highlanderFightingAttack", 2),
				ImageCache.getFrame("highlanderFightingAttack", 1)
		});
		
		takingDamageAnim = new GameAnimation(0.5f, new TextureRegion[] {
				ImageCache.getFrame("highlanderFightingAttack", 1),
				ImageCache.getFrame("highlanderFightingAttack", 1)	
		});
		
		startJumpingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("highlanderFightingAttack", 2),
				ImageCache.getFrame("highlanderFightingAttack", 2)
		});
		
		jumpingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("highlanderFightingAttack", 3)
		});
		
		attackingAnimation = new GameAnimation(0.2f, new TextureRegion[] {
				ImageCache.getFrame("highlanderFightingAttack", 4),
				ImageCache.getFrame("highlanderFightingAttack", 5),
				ImageCache.getFrame("highlanderFightingAttack", 6),
				ImageCache.getFrame("highlanderFightingAttack", 6),
				ImageCache.getFrame("highlanderFightingAttack", 4),
				ImageCache.getFrame("highlanderFightingAttack", 4)
		});
		
		initializeListeners();
		
		this.chanceOfIdling = 0.006f;
	}
}
