package fighting;

import java.util.Random;

import animations.GameAnimation;
import animations.GameAnimation.AnimationEvent;
import animations.GameAnimation.AnimationEventListener;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Fighter {
	
	public Vector3 pos;
	public Vector3 origPos;
	public Vector3 targetPos;
	
	public GameAnimation fightingIdleAnim;
	public GameAnimation fightingAttackAnim;
	public GameAnimation takingDamageAnim;
	
	public TextureRegion currTextureRegion;
	
	public int decalBatchNo = 0;
	
	public float animTime = 0.0f;
	boolean idle = true;
	boolean still = true;
	public boolean startAttacking = false;
	public float chanceOfIdling = 1.0f;
	
	public static Random rand = new Random();
	
	public Fighter(float x, float y, float z) {
		pos = new Vector3(x, y, z);
		origPos = new Vector3(x, y, z);
		targetPos = origPos;
	}
	
	public Fighter(float x, float y, float z, int decalBatchNo) {
		pos = new Vector3(x, y, z);
		origPos = new Vector3(x, y, z);
		targetPos = origPos;
		this.decalBatchNo = decalBatchNo;
	}
	
	
	public void update(float delta) {
		animTime += delta;
		if(idle) {
			standingStill();
		} else {
			currTextureRegion = fightingAttackAnim.getKeyFrame(animTime, false);
		}
	
	}
	
	public void standingStill() {
		if(still) { 
			float tryIdling = rand.nextFloat();
			if(tryIdling <= chanceOfIdling) {
				animTime = 0.0f;
				still = false;
			} else {
				currTextureRegion = fightingIdleAnim.getKeyFrame(0, false);
			}
		} else {
			currTextureRegion = fightingIdleAnim.getKeyFrame(animTime, false);
		}
	}
	
	public TextureRegion getTextureRegion() {
		return currTextureRegion;
	}
	
	public float getTextureHeight() {
		return currTextureRegion.getRegionHeight();
	}
	
	public float getTextureWidth() {
		return currTextureRegion.getRegionWidth();
	}

	public void initializeListeners() {
		fightingIdleAnim.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				still = true;
			}
			
		});
		
		fightingAttackAnim.addEventListener(new AnimationEventListener() {

			@Override
			public void onAnimationEnded(AnimationEvent e) {
				// TODO Auto-generated method stub
				idle = true;
				animTime = 0.0f;
			}
			
		});
	}
	
	public void attackMode(Vector3 target) {
		this.idle = false;
		animTime = 0.0f;
		targetPos = target;
		startAttacking = true;
	}
	
	
}
