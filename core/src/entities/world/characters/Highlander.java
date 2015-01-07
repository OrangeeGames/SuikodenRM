package entities.world.characters;

import animations.GameAnimation;
import animations.GameAnimation.AnimationEvent;
import animations.GameAnimation.AnimationEventListener;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.orangeegames.suikorm.SuikodenRM;

import entities.GameWorldCharacter;
import entities.Player;
import gamestate.BoxWorld;

public class Highlander extends GameWorldCharacter {

	boolean idle = true;
	float animTime;
	
	GameAnimation fightingIdleAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("highlanderFightingIdle", 1),
			ImageCache.getFrame("highlanderFightingIdle", 2),
			ImageCache.getFrame("highlanderFightingIdle", 3)
	});
	
	GameAnimation fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("highlanderFightingAttack", 1),
			ImageCache.getFrame("highlanderFightingAttack", 2),
			ImageCache.getFrame("highlanderFightingAttack", 3),
			ImageCache.getFrame("highlanderFightingAttack", 4),
			ImageCache.getFrame("highlanderFightingAttack", 5),
			ImageCache.getFrame("highlanderFightingAttack", 6),
			ImageCache.getFrame("highlanderFightingAttack", 5),
			ImageCache.getFrame("highlanderFightingAttack", 4),
			ImageCache.getFrame("highlanderFightingAttack", 3),
			ImageCache.getFrame("highlanderFightingAttack", 2),
			ImageCache.getFrame("highlanderFightingAttack", 1)
	});
	
	public Highlander(TextureRegion firstFrame, BoxWorld bw, float x, float y) {
		super(firstFrame, bw, x, y);
		
		animTime = 0.0f;
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("highlanderWalkDown", 1), 
				ImageCache.getFrame("highlanderWalkDown", 2), 
				ImageCache.getFrame("highlanderWalkDown", 3), 
				ImageCache.getFrame("highlanderWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("highlanderWalkLeft", 1), 
				ImageCache.getFrame("highlanderWalkLeft", 2), 
				ImageCache.getFrame("highlanderWalkLeft", 3), 
				ImageCache.getFrame("highlanderWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("highlanderWalkUp", 1), 
				ImageCache.getFrame("highlanderWalkUp", 2), 
				ImageCache.getFrame("highlanderWalkUp", 3), 
				ImageCache.getFrame("highlanderWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("highlanderWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("highlanderWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("highlanderWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
	}
	
	@Override
	public void interact(Player player) {
		idle = !idle;
		animTime = 0.0f;
		fightingAttackAnim.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				idle = true;
			}
			
		});
	}
	
	@Override
	public void update(float delta) {
		animTime += delta;
		if(idle) {
			this.setRegion(fightingIdleAnim.getKeyFrame(animTime, true));
			this.setWidth(fightingIdleAnim.getKeyFrame(animTime, true).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(fightingIdleAnim.getKeyFrame(animTime, true).getRegionHeight()*SuikodenRM.scale);
		} else {
			this.setRegion(fightingAttackAnim.getKeyFrame(animTime, false));
			this.setWidth(fightingAttackAnim.getKeyFrame(animTime, false).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(fightingAttackAnim.getKeyFrame(animTime, false).getRegionHeight()*SuikodenRM.scale);
		}
	}
	
}
