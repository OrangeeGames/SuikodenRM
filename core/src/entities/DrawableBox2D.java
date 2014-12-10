package entities;


import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;



public abstract class DrawableBox2D extends Box2DSprite implements Comparable<DrawableBox2D> {
 
        Body body;
       
        public abstract void interact(Player player);
       
        public DrawableBox2D(TextureRegion initialSprite) {
        	super(initialSprite);
        	setAdjustHeight(false);
        }
       
        public void draw(Batch spriteBatch) {
        	this.draw(spriteBatch, body);
        }
       
        @Override
        public int compareTo(DrawableBox2D arg0) {
                return Float.compare(arg0.body.getPosition().y, this.body.getPosition().y);
        }
        
        public Body getBody() {
        	return body;
        }
}

