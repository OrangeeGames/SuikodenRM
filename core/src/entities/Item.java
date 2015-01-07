package entities;

public class Item {

	// Headgear
	public static int CAP = 1;
	public static int HELMET = 1 << 2;
	
	// Body gear
	public static int LIGHT_ARMOR = 1 << 3;
	public static int HEAVY_ARMOR = 1 << 4;
	public static int VEST = 1 << 5;
	public static int ROBE = 1 << 6;
	
	// Shield
	public static int SHIELD = 1 << 7;
	
	// Accessories
	public static int MALE = 1 << 8;
	public static int FEMALE = 1 << 9;
	public static int KOBOLD = 1 << 10;
	public static int WINGER = 1 << 11;
	public static int NOBILITY = 1 << 12;
	public static int YOUTH = 1 << 13;
	
	// Other items
	public static int CONSUMABLES = 1 << 14;
	
	public String name;
	public int type;
	
	public Item(String name, int type) {
		this.name = name;
		this.type = type;
	}
	
	public Item() {
		this.name = "Unnamed";
		this.type = CONSUMABLES;
	}
	
	public boolean isHeadgear() {
		return (type & (CAP + HELMET)) != 0;
	}
	
	public boolean isBodygear() {
		return (type & (LIGHT_ARMOR + HEAVY_ARMOR + VEST + ROBE)) != 0;
	}
	
	public boolean isShield() {
		return (type & SHIELD) != 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
}
