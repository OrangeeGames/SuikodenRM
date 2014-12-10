package utilities;

import entities.GameCharacter;
import entities.characters.Killey;
import entities.characters.Leknaat;
import gamestate.BoxWorld;
import animations.ImageCache;

public class CharacterGeneration {

	
	public static GameCharacter getCharacter(String name, BoxWorld bw, float x, float y) {
		if(name.equals("Killey")) return new Killey(ImageCache.getFrame("killeyWalkLeft", 2), bw, x, y);
		if(name.equals("Leknaat")) return new Leknaat(ImageCache.getFrame("leknaatWalkLeft", 2), bw, x, y);
		return null;
	}
}
