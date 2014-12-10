package animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Anim {
    public TextureWrapper[] textures;
    public Vector2 pos;
    public AnimationProperty[] animationProperty;
    int currentFrame;
    int animCycles;
    float ticker;
    float timeLimit;
    Boolean paused;
    
    public Anim(int numberOfTextures,Vector2 pos){
          textures=new TextureWrapper[numberOfTextures];
          this.pos=pos;
    }

    public void addFrame(TextureAtlas atlas,String regionName,int index){
          textures[index]=new TextureWrapper(atlas,regionName);
    }
    public void set(int frame){ 
          currentFrame=(currentFrame+1)%animationProperty.length;
          timeLimit=animationProperty[currentFrame].timeLimit;
          ticker=0;
    }
    public void setAnimationProperty(AnimationProperty[] anim){
          animationProperty=anim;
          set(0);

    }
    public void update(float dt){
         if(!paused){
              ticker+=dt;
              if(ticker>timeLimit){
                  ticker-=timeLimit;
                  currentFrame=(currentFrame+1)%animationProperty.length;
                  timeLimit=animationProperty[currentFrame].timeLimit;
                  if(currentFrame==0)
                       animCycles++;
              }

         }
    }
    public  void draw(SpriteBatch sp){
         int texFrame=animationProperty[currentFrame].frame;
         textures[texFrame].draw(sp, pos, 0);
    } 
}