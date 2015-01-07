package entities;

import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.orangeegames.suikorm.SuikodenRM;

public class Player extends DrawableBox2D {
	
	private static int faceUP = 0;
	private static int faceDOWN = 1;
	private static int faceRIGHT = 2;
	private static int faceLEFT = 3;
	
	/** the movement velocity **/
	
	private float speed = 60f*SuikodenRM.scale;
	private float maxSpeed = 60f*SuikodenRM.scale;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	int currentDirection;
	
	private float dx = 0;
	private float dy = 0;
	private float animTime = 0;
	private TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("riouWalkLeft", 1));
	private TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("riouWalkLeft", 2));
	private TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("riouWalkLeft", 3));
	
	private GameAnimation currentWalkAnim;
	
	private static GameAnimation leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("riouWalkLeft", 1), 
			ImageCache.getFrame("riouWalkLeft", 2), 
			ImageCache.getFrame("riouWalkLeft", 3), 
			ImageCache.getFrame("riouWalkLeft", 2)});
	
	private static GameAnimation upAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("riouWalkUp", 1), 
			ImageCache.getFrame("riouWalkUp", 2), 
			ImageCache.getFrame("riouWalkUp", 3), 
			ImageCache.getFrame("riouWalkUp", 2)});

	private static GameAnimation rightAnim;
	
	private static GameAnimation downAnim = new GameAnimation(0.2f, new TextureRegion[]{
			ImageCache.getFrame("riouWalkDown", 1), 
			ImageCache.getFrame("riouWalkDown", 2), 
			ImageCache.getFrame("riouWalkDown", 3), 
			ImageCache.getFrame("riouWalkDown", 2)});
	
	
	public Player(Body body) {
		super(new TextureRegion(ImageCache.getFrame("riouWalkDown", 2)));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		//sprite = new TextureRegion(ImageCache.getFrame("riouWalkDown", 2));
		currentWalkAnim = downAnim;
		this.body = body;
		
		setAdjustWidth(false);

    	setHeight(walkRight1.getRegionHeight()*SuikodenRM.scale);
    	setWidth(walkRight1.getRegionWidth()*SuikodenRM.scale);
    	this.setCenterX(this.getOriginX()*SuikodenRM.scale-1*SuikodenRM.scale);
	}
	
	public void draw(Batch spriteBatch) {
		this.draw(spriteBatch, body);
	}
	
	public void update2(float delta) {
		if(left) {
			dx -= speed;
			if(dx < -maxSpeed)
				dx = -maxSpeed;
		}
		else if(right) {
			dx += speed;
			if(dx > maxSpeed)
				dx = maxSpeed;
		}
		else {
			dx = 0;
		}
		
		if(up) {
			dy += speed;
			if(dy > speed)
				dy = speed;
		}
		else if(down) {
			dy -= speed;
			if(dy < -speed)
				dy = -speed;
		}
		else {
			dy = 0;
		}
		
		body.setLinearVelocity(new Vector2(dx, dy));
		
		animTime += Gdx.graphics.getDeltaTime();
		if(isUp()) {
			currentWalkAnim = upAnim;
			this.setRegion(upAnim.getKeyFrame(animTime, true));
			this.setWidth(upAnim.getKeyFrame(animTime, true).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(upAnim.getKeyFrame(animTime, true).getRegionHeight()*SuikodenRM.scale);
			currentDirection = faceUP;
		}
		else if(isDown()) {
			currentWalkAnim = downAnim;
			this.setRegion(downAnim.getKeyFrame(animTime, true));
			this.setWidth(downAnim.getKeyFrame(animTime, true).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(downAnim.getKeyFrame(animTime, true).getRegionHeight()*SuikodenRM.scale);
			currentDirection = faceDOWN;
		}
		else if(isLeft()) {
			currentWalkAnim = leftAnim;
			this.setRegion(leftAnim.getKeyFrame(animTime, true));
			this.setWidth(leftAnim.getKeyFrame(animTime, true).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(leftAnim.getKeyFrame(animTime, true).getRegionHeight()*SuikodenRM.scale);
			currentDirection = faceLEFT;
		}
		else if(isRight()) {
			currentWalkAnim = rightAnim;
			this.setRegion(rightAnim.getKeyFrame(animTime, true));
			this.setWidth(rightAnim.getKeyFrame(animTime, true).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(rightAnim.getKeyFrame(animTime, true).getRegionHeight()*SuikodenRM.scale);
			currentDirection = faceRIGHT;
		}
		else {
			this.setRegion(currentWalkAnim.getKeyFrame(animTime, false));
			this.setWidth(currentWalkAnim.getKeyFrame(animTime, false).getRegionWidth()*SuikodenRM.scale);
			this.setHeight(currentWalkAnim.getKeyFrame(animTime, false).getRegionHeight()*SuikodenRM.scale);
		}

		
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
		if(!left && currentWalkAnim == leftAnim) {
			this.setTexture(leftAnim.getKeyFrame(animTime, false).getTexture());
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
		if(!right && currentWalkAnim == rightAnim) {
			this.setTexture(rightAnim.getKeyFrame(animTime, false).getTexture());
		}
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
		if(!up && currentWalkAnim == upAnim) {
			this.setTexture(upAnim.getKeyFrame(animTime, false).getTexture());
		}
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
		if(!down && currentWalkAnim == downAnim) {
			this.setTexture(downAnim.getKeyFrame(animTime, false).getTexture());
		}
	}
	
	public void interact() {
		
	}
	
	public boolean faceUp() {
		return currentDirection == faceUP;
	}
	
	public boolean faceDown() {
		return currentDirection == faceDOWN;
	}
	
	public boolean faceRight() {
		return currentDirection == faceRIGHT;
	}
	
	public boolean faceLeft() {
		return currentDirection == faceLEFT;
	}

	@Override
	public void interact(Player player) {
		
	}
	
	public void dispose() {
		getTexture().dispose();
	}
	
}
