package entities.characters;

import entities.GameCharacter;
import gamestate.BoxWorld;
import animations.GameAnimation;
import animations.ImageCache;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Killey extends GameCharacter {

	

	public Killey(TextureRegion firstFrame, BoxWorld bw, float x, float y) {
		super(firstFrame, bw, x, y);
		
		name = "Killey";
		
		downAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkDown", 1), 
				ImageCache.getFrame("killeyWalkDown", 2), 
				ImageCache.getFrame("killeyWalkDown", 3), 
				ImageCache.getFrame("killeyWalkDown", 2)});
		
		leftAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkLeft", 1), 
				ImageCache.getFrame("killeyWalkLeft", 2), 
				ImageCache.getFrame("killeyWalkLeft", 3), 
				ImageCache.getFrame("killeyWalkLeft", 2)});
		
		upAnim = new GameAnimation(0.2f, new TextureRegion[]{
				ImageCache.getFrame("killeyWalkUp", 1), 
				ImageCache.getFrame("killeyWalkUp", 2), 
				ImageCache.getFrame("killeyWalkUp", 3), 
				ImageCache.getFrame("killeyWalkUp", 2)});
		
		TextureRegion walkRight1 = 	new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 1));
		TextureRegion walkRight2 =  new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 2));
		TextureRegion walkRight3 =  new TextureRegion(ImageCache.getFrame("killeyWalkLeft", 3));
		
		walkRight1.flip(true, false);
		walkRight2.flip(true, false);
		walkRight3.flip(true, false);
		
		rightAnim = new GameAnimation(0.2f, new TextureRegion[]{
				walkRight1, 
				walkRight2, 
				walkRight3, 
				walkRight2});
		
		facePicture = ImageCache.getTexture("head-killey");
		
		messages.add("Hi Tester!");
		messages.add("Thank you for joining up in the test of Suikoden RM.");
		messages.add("For now, not much have happened. Hopefully this will change in a near future. I'm reaching out to people who might know a thing or two about LibGDX!");
		messages.add("And if someone checks the code, I know that's no beauty. But it's working, and that at least something.");
		messages.add("If you are reading this message, you are reading it from inside the code. Thank you for at least taking your time to look around");
	}

}
