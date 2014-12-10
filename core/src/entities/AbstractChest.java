package entities;

import gamestate.BoxWorld;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.orangeegames.suikorm.SuikodenRM;

public class AbstractChest extends DrawableBox2D {

	public static TextureRegion closedChest = ImageCache.getFrame("chest", 1);
	public static TextureRegion openChest = ImageCache.getFrame("chest", 2);
	protected boolean open;
	
	public AbstractChest(boolean open, BoxWorld bw, float x, float y) {
		super(closedChest);
		this.open = open;
		if(open) {
			this.setTexture(openChest.getTexture());
		}
		
		
		BodyDef chest = new BodyDef();
		chest.type = BodyDef.BodyType.StaticBody;
		chest.position.set(new Vector2(x, y));

		body = bw.getWorld().createBody(chest);
		body.setUserData(this);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10*SuikodenRM.scale, 10*SuikodenRM.scale);
		body.createFixture(shape, 0f);
		this.setHeight(closedChest.getRegionHeight()*SuikodenRM.scale);
		this.setAdjustWidth(false);
		this.setWidth(closedChest.getRegionWidth()*SuikodenRM.scale);
	}
	
	public void interact(Player player) {
		if(SuikodenRM.debug) {
			System.out.println("OPENED");
		}
		
		this.setRegion(openChest);
		this.setHeight(openChest.getRegionHeight()*SuikodenRM.scale);
		this.setWidth(openChest.getRegionWidth()*SuikodenRM.scale);
		
		if(SuikodenRM.debug){
			System.out.println(this.getOriginX() + " : " + this.getOriginY());
		}
		SuikodenRM.gsm.setInfo("Test");
	}

}
