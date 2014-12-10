package animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TextureWrapper {
    public TextureRegion textureRegion;
    int width;
    int height;
    float scaleX;
    float scaleY;
    int originX;
    int originY;
    
    
    public TextureWrapper(TextureAtlas atlas,String regionName){
          // For above textures reigon names would be name of png like Animation1,Animation2 etc
          textureRegion = atlas.findRegion(regionName);    
          width = textureRegion.getRegionWidth()/2;
          height = textureRegion.getRegionHeight()/2;
          // Origin is the pivot for rotation. width/2 and height/2 set image's center as pivot for rotation
          originX=width/2;
          originY=height/2;
          scaleX=1;
          scaleY=1;
    } 

    public void draw(SpriteBatch sp, Vector2 pos, int rotation){
                 sp.draw(textureRegion,
                                    pos.x-width/2,   //To position it in center horizontally
                                    pos.y-height/2,  //To position it in center vertically
                                    originX,
                                     originY,  
                                     width,    //width of texture
                                     height,    //height of texture
                                     scaleX,   //Negative value will flip the texture horizontally
                                     scaleY,   //Negative value will flip the texture vertically
                                     rotation //Rotation angle in degrees
                                     );
    } 

}