package entities.characters;

import entities.GameCharacter;
import gamestate.BoxWorld;
import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Leknaat extends GameCharacter {

	

	public Leknaat(TextureRegion firstFrame, BoxWorld bw, float x, float y) {
		super(firstFrame, bw, x, y);
		
		name = "Leknaat";
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("leknaatWalkDown", 1), 
				ImageCache.getFrame("leknaatWalkDown", 2), 
				ImageCache.getFrame("leknaatWalkDown", 3), 
				ImageCache.getFrame("leknaatWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("leknaatWalkLeft", 1), 
				ImageCache.getFrame("leknaatWalkLeft", 2), 
				ImageCache.getFrame("leknaatWalkLeft", 3), 
				ImageCache.getFrame("leknaatWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("leknaatWalkUp", 1), 
				ImageCache.getFrame("leknaatWalkUp", 2), 
				ImageCache.getFrame("leknaatWalkUp", 3), 
				ImageCache.getFrame("leknaatWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("leknaatWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("leknaatWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("leknaatWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		facePicture = ImageCache.getTexture("head-leknaat");
		
		messages.add("If this game takes off, I'll have a big chunk of the story! When you find out what RM stands for, you might also understand why I'm here.");
		messages.add("Hopefully this will see through, because I really enjoy the story that has been nagging me, every since I saw the final ending of Suikoden III.");
		messages.add("Well, I'll see you around, and next time, I might visit you in your dreams? Sleep tight!");
		messages.add("And if someone checks the code, I know that's no beauty. But it's working, and that at least something.");
		messages.add("If you are reading this message, you are reading it from inside the code. Thank you for at least taking your time to look around");
	}

}
