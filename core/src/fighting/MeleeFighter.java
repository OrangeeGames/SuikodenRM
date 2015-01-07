package fighting;

import com.badlogic.gdx.math.Vector3;

import animations.GameAnimation;
import animations.GameAnimation.AnimationEvent;
import animations.GameAnimation.AnimationEventListener;

public class MeleeFighter extends Fighter {

	public GameAnimation startJumpingAnimation;
	public GameAnimation jumpingAnimation;
	public GameAnimation attackingAnimation;
	
	private boolean jumping = false;
	private boolean attacking = false;
	private boolean reverseJumping = false;
	
	public MeleeFighter(float x, float y, float z) {
		super(x, y, z);
	}
	
	public MeleeFighter(float x, float y, float z, int decalBatchNo) {
		super(x, y, z, decalBatchNo);
	}
	
	
	@Override
	public void update(float delta) {
		animTime += delta;
		
		if(startAttacking) {
			currTextureRegion = startJumpingAnimation.getKeyFrame(animTime, false);
		}
		else if(jumping) {
			currTextureRegion = jumpingAnimation.getKeyFrame(animTime, true);
			Vector3 anglePos = new Vector3((targetPos.x - pos.x), targetPos.y-pos.y, targetPos.z-pos.z);
			anglePos.nor();
			pos.add(anglePos.x*delta, anglePos.y*delta, anglePos.z*delta);
			if(pos.dst(targetPos) < 0.05) {
				currTextureRegion = jumpingAnimation.getKeyFrame(animTime, false);
			}
		}
		else if(attacking) {
			currTextureRegion = attackingAnimation.getKeyFrame(animTime, false);
		}
		else if(reverseJumping) {
			currTextureRegion = jumpingAnimation.getKeyFrame(animTime, true);
			Vector3 anglePos = new Vector3((origPos.x - pos.x), origPos.y-pos.y, origPos.z - pos.z);
			anglePos.nor();
			pos.add(anglePos.x * delta, anglePos.y*delta, anglePos.z*delta);
			if(pos.dst(origPos) < 0.05) {
				currTextureRegion = jumpingAnimation.getKeyFrame(animTime, false);
			}
		}
		else {
			this.standingStill();
		}
	}
	
	@Override
	public void initializeListeners() {
		super.initializeListeners();
		
		jumpingAnimation.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				animTime = 0.0f;
				if(jumping) {
					attacking = true;
					jumping = false;
				} else if(reverseJumping) {
					idle = true;
					reverseJumping = false;
				}
			}
			
		});
		
		attackingAnimation.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				animTime = 0.0f;
				reverseJumping = true;
				attacking = false;
			}
			
		});
		

		startJumpingAnimation.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				animTime = 0.0f;
				jumping = true;
				startAttacking = false;
			}
			
		});
	}

}
