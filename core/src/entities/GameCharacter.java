package entities;

import animations.GameAnimation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameCharacter {
	
	protected PlayerStatus ps;
	protected GameAnimation leftAnim;
	protected GameAnimation upAnim;
	protected GameAnimation rightAnim;
	protected GameAnimation downAnim;
	
	protected TextureRegion facePicture;
	
	protected String name;
	protected Weapon weapon;
	protected CharacterEquipment equipment;
	
	public GameCharacter() {
		ps = new PlayerStatus();	
	}
	
	public String getName() {
		return name;
	}
	
	public TextureRegion getFacePicture() {
		return facePicture;
	}
	
	public PlayerStatus getPlayerStatus() {
		return ps;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public int getATK() {
		return ps.getSTRENGTH() + weapon.getATK();
	}
	
	public int getDEF() {
		return ps.getPROT();
	}
	
	public CharacterEquipment getEquipment() {
		return equipment;
	}
}
