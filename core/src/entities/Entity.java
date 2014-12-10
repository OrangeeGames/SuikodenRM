package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.orangeegames.suikorm.SuikodenRM;

public abstract class Entity extends Sprite {

	
	public Entity(Sprite sprite, float x, float y) {
		super(sprite);
		this.setPosition(x/SuikodenRM.scale, y/SuikodenRM.scale);
	}
	
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	protected abstract void update(float delta);
	
}
