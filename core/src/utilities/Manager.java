package utilities;

import com.badlogic.gdx.assets.AssetManager;

import entities.Inventory;

public class Manager extends AssetManager{
	//Rotations
	public static final int ROT_LEFT = 90;
	public static final int ROT_RIGHT = 270;
	public static final int ROT_UP = 180;
	public static final int ROT_DOWN = 0;
	
	public static Party party = new Party();
	public static Inventory inventory = new Inventory();
	
	public void init() {
		
	}
}
