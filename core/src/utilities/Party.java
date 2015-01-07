package utilities;

import java.util.ArrayList;

import entities.GameCharacter;
import entities.Rune;

public class Party {

	public ArrayList<GameCharacter> frontRow;
	public ArrayList<GameCharacter> backRow;
	
	public Party() {
		frontRow = new ArrayList<GameCharacter>();
		backRow = new ArrayList<GameCharacter>();
		
		init();
	}
	
	private void init() {
		frontRow.add(CharacterGeneration.getGameCharacter("Killey"));
		frontRow.get(0).getPlayerStatus().setHeadRune(new Rune("Gale"));
		frontRow.add(CharacterGeneration.getGameCharacter("Killey"));

		frontRow.add(CharacterGeneration.getGameCharacter("Clive"));

		backRow.add(CharacterGeneration.getGameCharacter("Clive"));

		backRow.add(CharacterGeneration.getGameCharacter("Clive"));

		backRow.add(CharacterGeneration.getGameCharacter("Clive"));
	}
	
	public int size() {
		return frontRow.size() + backRow.size();
	}
	
	public GameCharacter getCharacter(int i) {
		System.out.println(i);
		if(i >= frontRow.size()) {
			return backRow.get(i - frontRow.size());
		} else {
			return frontRow.get(i);
		}
	}
}
