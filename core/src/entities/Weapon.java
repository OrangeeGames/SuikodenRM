package entities;

public class Weapon {

	public static int MELEE = 0;
	public static int RANGE = 1;
	public static int MIDDLE = 2;
	
	public static int BOW = 0;
	public static int AXE = 1;
	public static int DARTS = 2;
	public static int CLAWS = 3;
	public static int TWO_HAND = 4;
	public static int SPEAR = 5;
	public static int ROD = 6;
	public static int ONE_HAND = 7;
	public static int OTHER = 8;
	public static int TALKING_SWORD = 9;
	public static int HERO = 10;
	public static int NANAMI = 11;
	
	private static int[][] WEAPON_LIST = {
		{4, 6, 7, 10, 13, 25, 33, 40, 48, 57, 65, 73, 98, 116, 135, 147},
		{8, 9, 10, 11, 13, 17, 23, 35, 43, 52, 60, 72, 85, 135, 156, 175},
		{6,9,12,20,25,31,37,43,48,70,83,95,108,119,130,140},
		{7,8,9,10,39,42,45,48,51,53,110,114,118,124,130,135},
		{7,9,12,15,19,31,39,46,55,64,73,97,111,129,147,160},
		{5,8,12,24,32,37,42,48,53,80,88,98,110,120,130,140},
		{3,4,5,7,9,11,26,33,42,50,58,64,80,87,94,100},
		{5,7,9,12,15,27,35,42,50,59,67,90,102,120,138,150},
		{5,6,8,11,14,17,33,40,47,56,66,76,89,124,145,165},
		{8,11,14,17,22,34,42,50,59,69,78,102,117,136,155,170},
		{5,6,8,11,14,18,35,42,49,58,68,79,92,127,148,170},
		{7,9,11,12,17,22,37,44,51,59,69,78,91,125,145,165}
	};
	
	private static int[][] LEVEL_TYPE = {
		{5, 13},
		{7, 13},
		{3, 9},
		{4, 10},
		{5, 11},
		{3, 9},
		{6, 12},
		{5, 11},
		{6, 13},
		{5, 11},
		{6, 13},
		{6, 13}
	};
	
	private static int[] WEAPON_TYPE = {RANGE, MELEE, RANGE, MELEE, MELEE, MIDDLE, RANGE, MIDDLE, MELEE, MELEE, MIDDLE, MIDDLE};
	
	private String[] NAMES;
	private int LEVEL;
	private int TYPE;
	private Rune runes;
	
	
	public Weapon(String[] names, int level, int type, Rune rune) {
		this.NAMES = names;
		this.LEVEL = level;
		this.TYPE = type;
		this.runes = rune;
	}
	
	public String getNAME() {
		if(LEVEL_TYPE[TYPE][1] <= LEVEL) {
			return NAMES[2];
		} else if(LEVEL_TYPE[TYPE][0] <= LEVEL) {
			return NAMES[1];
		} else {
			return NAMES[0];
		}
	}

	public int getLEVEL() {
		return LEVEL;
	}

	public void setLEVEL(int lEVEL) {
		LEVEL = lEVEL;
	}

	public int getATK() {
		return WEAPON_LIST[TYPE][LEVEL-1];
	}
	
	public Rune getRunes() {
		return runes;
	}

	public void setRunes(Rune runes) {
		this.runes = runes;
	}
	
	public String getTypeString() {
		if(WEAPON_TYPE[TYPE] == MELEE) {
			return "M";
		} else if(WEAPON_TYPE[TYPE] == RANGE) {
			return "R";
		} else {
			return "S";
		}
	}
	
}
