package utilities;

import animations.ImageCache;
import entities.GameCharacter;
import entities.GameWorldCharacter;
import entities.game.characters.GameClive;
import entities.game.characters.GameKilley;
import entities.world.characters.Clive;
import entities.world.characters.Killey;
import entities.world.characters.Leknaat;
import gamestate.BoxWorld;

public class CharacterGeneration {

	
	public static GameWorldCharacter getWorldCharacter(String name, BoxWorld bw, float x, float y) {
		if(name.equals("Killey")) return new Killey(ImageCache.getFrame("killeyWalkLeft", 2), bw, x, y);
		else if(name.equals("Leknaat")) return new Leknaat(ImageCache.getFrame("leknaatWalkLeft", 2), bw, x, y);
		else if(name.equals("Clive")) return new Clive(ImageCache.getFrame("cliveWalkLeft", 2), bw, x, y);
		return null;
	}
	
	public static GameCharacter getGameCharacter(String name) {
		if(name.equals("Clive")) return new GameClive();
		if(name.equals("Killey")) return new GameKilley();
		return null;
	}
}
