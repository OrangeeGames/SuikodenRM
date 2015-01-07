package entities.world.characters;

import animations.GameAnimation;
import animations.ImageCache;
import animations.GameAnimation.AnimationEvent;
import animations.GameAnimation.AnimationEventListener;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.orangeegames.suikorm.SuikodenRM;

import entities.GameWorldCharacter;
import entities.Player;
import gamestate.BoxWorld;

public class Clive extends GameWorldCharacter {

	boolean idle = true;
	float animTime;
	
	GameAnimation fightingIdleAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("cliveFightingIdle", 1),
			ImageCache.getFrame("cliveFightingIdle", 2),
			ImageCache.getFrame("cliveFightingIdle", 3),
			ImageCache.getFrame("cliveFightingIdle", 4)
	});
	
	GameAnimation fightingAttackAnim = new GameAnimation(0.2f, new TextureRegion[]{
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

	public Clive(TextureRegion firstFrame, BoxWorld bw, float x, float y) {
		super(firstFrame, bw, x, y);
		
		animTime = 0.0f;
		
		name = "Clive";
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkDown", 1), 
				ImageCache.getFrame("cliveWalkDown", 2), 
				ImageCache.getFrame("cliveWalkDown", 3), 
				ImageCache.getFrame("cliveWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkLeft", 1), 
				ImageCache.getFrame("cliveWalkLeft", 2), 
				ImageCache.getFrame("cliveWalkLeft", 3), 
				ImageCache.getFrame("cliveWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("cliveWalkUp", 1), 
				ImageCache.getFrame("cliveWalkUp", 2), 
				ImageCache.getFrame("cliveWalkUp", 3), 
				ImageCache.getFrame("cliveWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("cliveWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		facePicture = ImageCache.getTexture("head-clive");
		
		messages.add("Hi Tester!");
		messages.add("Thank you for joining up in the test of Suikoden RM.");
		messages.add("For now, not much have happened. Hopefully this will change in a near future. I'm reaching out to people who might know a thing or two about LibGDX!");
		messages.add("And if someone checks the code, I know that's no beauty. But it's working, and that at least something.");
		messages.add("If you are reading this message, you are reading it from inside the code. Thank you for at least taking your time to look around");
		
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
		if(isFighting()) {
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
		} else {
			super.update(delta);
		}
	}

}
