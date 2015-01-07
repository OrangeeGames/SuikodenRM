package entities;

import gamestate.BoxWorld;

import java.util.ArrayList;
import java.util.List;

import animations.GameAnimation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.orangeegames.suikorm.SuikodenRM;

public abstract class GameWorldCharacter extends DrawableBox2D {
	
	protected float dx = 0;
	protected float dy = 0;
	protected float animTime = 0;
	
	protected GameAnimation currentWalkAnim;
	
	protected GameAnimation leftAnim;
	protected GameAnimation upAnim;
	protected GameAnimation rightAnim;
	protected GameAnimation downAnim;
	
	protected TextureRegion facePicture;
	
	protected String name;
	
	protected ArrayList<String> messages = new ArrayList<String>();
	
	protected int startMessage = 0;
	protected int stopMessage = 0;
	
	protected boolean moveable = true;
	private boolean fighting = false;
	
	public GameWorldCharacter(TextureRegion firstFrame, BoxWorld bw, float x, float y) {
		super(firstFrame);
		
		BodyDef gameCharacter = new BodyDef();
		gameCharacter.type = BodyDef.BodyType.StaticBody;
		gameCharacter.position.set(new Vector2(x, y));

		body = bw.getWorld().createBody(gameCharacter);
		body.setUserData(this);
		
		Vector2[] vec = new Vector2[4];
		vec[0] = new Vector2(7.8f*SuikodenRM.scale, 7.8f*SuikodenRM.scale);
		vec[1] = new Vector2(0f*SuikodenRM.scale, 3.9f*SuikodenRM.scale);
		vec[2] = new Vector2(7.8f*SuikodenRM.scale, 0f*SuikodenRM.scale);
		vec[3] = new Vector2(15.6f*SuikodenRM.scale, 3.9f*SuikodenRM.scale);
		
		PolygonShape shape = new PolygonShape();
		shape.set(vec);
		
		body.createFixture(shape, 0f);
		shape.dispose();

		this.setAdjustWidth(false);
		
		setHeight(firstFrame.getRegionHeight()*SuikodenRM.scale);
    	setWidth(firstFrame.getRegionWidth()*SuikodenRM.scale);

		this.setCenterX(this.getOriginX()*SuikodenRM.scale-4*SuikodenRM.scale);
		
	}
	
	public void draw(Batch spriteBatch) {
		this.draw(spriteBatch, body);
	}
	
	public void interact(Player player) {
		if(moveable) {
			TextureRegion tr = null;
			float diffX = this.getBody().getPosition().x - player.getBody().getPosition().x;
			float diffY = this.getBody().getPosition().y - player.getBody().getPosition().y;
			System.out.println("X: " + diffX + " , Y: " + diffY);
			if(diffX > 8*SuikodenRM.scale) {
				tr = leftAnim.getKeyFrame(0.3f, false);
			} else if(diffX < -8*SuikodenRM.scale) {
				tr = rightAnim.getKeyFrame(0.3f, false);
			} else if(diffY > 8*SuikodenRM.scale) {
				tr = downAnim.getKeyFrame(0.3f, false);
			} else if(diffY < -8*SuikodenRM.scale) {
				tr = upAnim.getKeyFrame(0.3f, false);
			}
			
			if(tr != null) {
				this.setRegion(tr);
				this.setHeight(tr.getRegionHeight()*SuikodenRM.scale);
				this.setWidth(tr.getRegionWidth()*SuikodenRM.scale);
			}
		}
		SuikodenRM.gsm.setMessage(this);
	}
	
	public String getName() {
		return name;
	}
	
	public TextureRegion getFacePicture() {
		return facePicture;
	}
	
	public List<String> getMessages() {
		return messages.subList(startMessage, stopMessage);
	}

	public void setMessage(int startMessage, int stopMessage) {
		this.startMessage = startMessage;
		this.stopMessage = stopMessage;
	}
	
	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}
	
	public void update(float delta) {
		
	}

	public boolean isFighting() {
		return fighting;
	}

	public void setFighting(boolean fighting) {
		this.fighting = fighting;
	}
}
