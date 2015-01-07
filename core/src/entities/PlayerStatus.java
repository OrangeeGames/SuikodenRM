package entities;

public class PlayerStatus {

	/*
Rank	BaseStat	Lv02-19		Lv20-59		Lv60-99		Lv 99 Stat Range
S		14			4-5(25%)	2-3(75%)	0-1(50%)	166-255(230)

A+		14			3-4(60%)	2-3(60%)	0-1(50%)	148-246(210)

A		14			4-5(90%)	1-2(90%)	0-1(50%)	126-224(210)

B+		13			3-4(25%)	2-3(25%)	0-1(50%)	147-245(180)

B		13			2-3(90%)	1-2(90%)	0-1(50%)	89-187(165)

C+		12			2-3(40%)	1-2(75%)	0-1(50%)	88-186(145)

C		12			2-3(10%)	1-2(40%)	0-1(50%)	88-186(125)
	
D+		11			1-2(60%)	1-2(10%)	0-1(50%)	69-167(105)

D		11			1-2(40%)	0-1(90%)	0-1(50%)	29-127(95)

E+		10			1-2(10%)	0-1(40%)	0-1(50%)	28-126(65)

E		10			0-1(90%)	0-1(60%)	0-1(50%)	10-108(65)
	
	*/
	
	public static int TYPE_S = 0;
	public static int TYPE_AP = 1;
	public static int TYPE_A = 2;
	public static int TYPE_BP = 3;
	public static int TYPE_B = 4;
	public static int TYPE_CP = 5;
	public static int TYPE_C = 6;
	public static int TYPE_DP = 7;
	public static int TYPE_D = 8;
	public static int TYPE_EP = 9;
	public static int TYPE_E = 10;
	public static int TYPE_F = 11;
	
	private int LEVEL;
	private int EXP;

	private int STRENGTH;
	private int TECH;
	private int MAGIC;
	private int SPEED;
	private int PROT;
	private int M_DEF;
	private int LUCK;

	private int STR_TYPE;
	private int TECH_TYPE;
	private int MAGIC_TYPE;
	private int SPEED_TYPE;
	private int PROT_TYPE;
	private int M_DEF_TYPE;
	private int LUCK_TYPE;
	private int HP_TYPE;
	
	private int WEP_LVL;
	
	private int HP;
	private int MAX_HP;
	
	private int RUNE_1;
	private int RUNE_2;
	private int RUNE_3;
	private int RUNE_4;
	
	private int MAX_RUNE_1;
	private int MAX_RUNE_2;
	private int MAX_RUNE_3;
	private int MAX_RUNE_4;
	
	private Rune rightHandRune;
	private Rune leftHandRune;
	private Rune headRune;
	
	public PlayerStatus() {
		this.LEVEL = 1;
		this.EXP = 0;
		this.STRENGTH = 0;
		this.TECH = 0;
		this.MAGIC = 0;
		this.SPEED = 0;
		this.PROT = 0;
		this.M_DEF = 0;
		this.LUCK = 0;
		this.WEP_LVL = 1;
		this.HP = 1;
		this.MAX_HP = 1;
		this.RUNE_1 = 0;
		this.RUNE_2 = 0;
		this.RUNE_3 = 0;
		this.RUNE_4 = 0;
		this.MAX_RUNE_1 = 0;
		this.MAX_RUNE_2 = 0;
		this.MAX_RUNE_3 = 0;
		this.MAX_RUNE_4 = 0;
		this.rightHandRune = null;
		this.leftHandRune = null;
		this.headRune = null;
	}
	
	public PlayerStatus(int level, int exp, int str, int tech, int magic, int speed, int prot, int m_def, int luck, 
			int atk, int def, int wep_lvl, int hp, int max_hp, int rune_1, int rune_2, int rune_3, int rune_4, int max_rune_1,
			int max_rune_2, int max_rune_3, int max_rune_4, Rune rightHandRune, Rune leftHandRune, Rune headRune) {
		this.LEVEL = level;
		this.EXP = exp;
		this.STRENGTH = str;
		this.TECH = tech;
		this.MAGIC = magic;
		this.SPEED = speed;
		this.PROT = prot;
		this.M_DEF = m_def;
		this.LUCK = luck;
		this.WEP_LVL = wep_lvl;
		this.HP = hp;
		this.MAX_HP = max_hp;
		this.RUNE_1 = rune_1;
		this.RUNE_2 = rune_2;
		this.RUNE_3 = rune_3;
		this.RUNE_4 = rune_4;
		this.MAX_RUNE_1 = max_rune_1;
		this.MAX_RUNE_2 = max_rune_2;
		this.MAX_RUNE_3 = max_rune_3;
		this.MAX_RUNE_4 = max_rune_4;
		this.rightHandRune = rightHandRune;
		this.leftHandRune = leftHandRune;
		this.headRune = headRune;
	}
	
	public PlayerStatus(int lvl, int str_type, int tech_type, int magic_type, int speed_type, int prot_type, int m_def_type, int luck_type, int hp_type) {
		this.LEVEL = 1;
		this.EXP = 0;
		this.STRENGTH = 0;
		this.TECH = 0;
		this.MAGIC = 0;
		this.SPEED = 0;
		this.PROT = 0;
		this.M_DEF = 0;
		this.LUCK = 0;
		this.WEP_LVL = 1;
		this.HP = 1;
		this.MAX_HP = 1;
		this.RUNE_1 = 0;
		this.RUNE_2 = 0;
		this.RUNE_3 = 0;
		this.RUNE_4 = 0;
		this.MAX_RUNE_1 = 0;
		this.MAX_RUNE_2 = 0;
		this.MAX_RUNE_3 = 0;
		this.MAX_RUNE_4 = 0;
		this.rightHandRune = null;
		this.leftHandRune = null;
		this.headRune = null; 
		
		this.STR_TYPE = str_type;
		this.TECH_TYPE = tech_type;
		this.MAGIC_TYPE = magic_type;
		this.SPEED_TYPE = speed_type;
		this.PROT_TYPE = prot_type;
		this.M_DEF_TYPE = m_def_type;
		this.LUCK_TYPE = luck_type;
		this.HP_TYPE = hp_type;
		calculateStartingValues(lvl);
	}
	
	private void calculateStartingValues(int lvl) {
		STRENGTH = getBaseStat(STR_TYPE);
		TECH = getBaseStat(TECH_TYPE);
		MAGIC = getBaseStat(MAGIC_TYPE);
		SPEED = getBaseStat(SPEED_TYPE);
		PROT = getBaseStat(PROT_TYPE);
		M_DEF = getBaseStat(M_DEF_TYPE);
		LUCK = getBaseStat(LUCK_TYPE);
		MAX_HP = getBaseHP(HP_TYPE);
		while(lvl != 1) {
			lvlUp();
			lvl--;
		}
	}
		
	public void lvlUp() {
		LEVEL++;
		STRENGTH += lvlUpStat(STR_TYPE, LEVEL);
		TECH += lvlUpStat(TECH_TYPE, LEVEL);
		MAGIC += lvlUpStat(MAGIC_TYPE, LEVEL);
		SPEED += lvlUpStat(SPEED_TYPE, LEVEL);
		PROT += lvlUpStat(PROT_TYPE, LEVEL);
		M_DEF += lvlUpStat(M_DEF_TYPE, LEVEL);
		LUCK += lvlUpStat(LUCK_TYPE, LEVEL);
		MAX_HP += lvlUpHP(HP_TYPE, LEVEL);
		checkMagic();
		HP = MAX_HP;
		RUNE_1 = MAX_RUNE_1;
		RUNE_2 = MAX_RUNE_2;
		RUNE_3 = MAX_RUNE_3;
		RUNE_4 = MAX_RUNE_4;
	}
	
	private void checkMagic() {
		if(MAGIC > 250) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 9;
			MAX_RUNE_3 = 7;
			MAX_RUNE_4 = 5;
		} else if(MAGIC > 240) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 9;
			MAX_RUNE_3 = 7;
			MAX_RUNE_4 = 4;
		} else if(MAGIC > 220) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 9;
			MAX_RUNE_3 = 6;
			MAX_RUNE_4 = 4;
		} else if(MAGIC > 200) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 8;
			MAX_RUNE_3 = 5;
			MAX_RUNE_4 = 3;
		} else if(MAGIC > 175) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 7;
			MAX_RUNE_3 = 4;
			MAX_RUNE_4 = 2;
		} else if(MAGIC > 160) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 6;
			MAX_RUNE_3 = 3;
			MAX_RUNE_4 = 2;
		} else if(MAGIC > 150) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 6;
			MAX_RUNE_3 = 3;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 140) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 5;
			MAX_RUNE_3 = 3;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 135) {
			MAX_RUNE_1 = 9;
			MAX_RUNE_2 = 5;
			MAX_RUNE_3 = 2;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 125) {
			MAX_RUNE_1 = 8;
			MAX_RUNE_2 = 5;
			MAX_RUNE_3 = 2;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 115) {
			MAX_RUNE_1 = 8;
			MAX_RUNE_2 = 4;
			MAX_RUNE_3 = 2;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 100) {
			MAX_RUNE_1 = 7;
			MAX_RUNE_2 = 4;
			MAX_RUNE_3 = 2;
			MAX_RUNE_4 = 1;
		} else if(MAGIC > 95) {
			MAX_RUNE_1 = 7;
			MAX_RUNE_2 = 3;
			MAX_RUNE_3 = 1;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 75) {
			MAX_RUNE_1 = 6;
			MAX_RUNE_2 = 3;
			MAX_RUNE_3 = 1;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 60) {
			MAX_RUNE_1 = 5;
			MAX_RUNE_2 = 2;
			MAX_RUNE_3 = 1;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 55) {
			MAX_RUNE_1 = 4;
			MAX_RUNE_2 = 2;
			MAX_RUNE_3 = 0;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 45) {
			MAX_RUNE_1 = 4;
			MAX_RUNE_2 = 1;
			MAX_RUNE_3 = 0;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 30) {
			MAX_RUNE_1 = 3;
			MAX_RUNE_2 = 1;
			MAX_RUNE_3 = 0;
			MAX_RUNE_4 = 0;
		} else if(MAGIC > 15) {
			MAX_RUNE_1 = 2;
			MAX_RUNE_2 = 0;
			MAX_RUNE_3 = 0;
			MAX_RUNE_4 = 0;
		}
	}
	
	private static double[][] luckPercent = {
		{0.25,0.60,0.90,0.25,0.90,0.40,0.10,0.60,0.40,0.10,0.90,0.50},
		{0.75,0.60,0.90,0.25,0.90,0.75,0.40,0.10,0.90,0.40,0.60,0.50}
	};
	
	private static int[][] statLucky = {
		{5,4,5,4,3,3,3,2,2,2,1},
		{3,3,2,3,2,2,2,2,1,1,1}
	};

	private static int[][] statNotLucky = {
		{4,3,4,3,3,2,2,1,1,1,0},
		{2,2,1,2,1,1,1,1,0,0,0}
	};

	private static int[][] hpLucky = {
		{24,15,20,13,12,10,9,8,7,6,4,8},
		{10,14,10,13,12,12,10,9,9,8,7,5}
	};
	
	private static int[][] hpNotLucky = {
		{23,13,18,11,10,8,7,7,5,4,3,7},
		{9,13,8,11,10,10,9,8,7,6,5,2}
	};
	
	
	private static int[] baseStat = {14,14,14,13,13,12,12,11,11,10,10};
	
	private static int[] baseHP = {70,30,50,28,26,24,16,13,10,10,6};
	
	private int getBaseStat(int type) {
		return baseStat[type];
	}
	
	
	private int getBaseHP(int type) {
		return baseHP[type];
	}
	

	public int lvlUpStat(int type, int lvl) {
		double randomLuck = Math.random();
		if(lvl < 20) {
			if(luckPercent[0][type] < randomLuck) {
				return statLucky[0][type];
			} else {
				return statNotLucky[0][type];
			}
		} else if(lvl < 60) {
			if(luckPercent[1][type] < randomLuck) {
				return statLucky[1][type];
			} else {
				return statNotLucky[1][type];
			}
		} else {
			if(randomLuck < 0.5) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	
	public int lvlUpHP(int type, int lvl) {
		double randomLuck = Math.random();
		if(lvl < 20) {
			if(luckPercent[0][type] < randomLuck) {
				return hpLucky[0][type];
			} else {
				return hpNotLucky[0][type];
			}
		} else if(lvl < 60) {
			if(luckPercent[1][type] < randomLuck) {
				return hpLucky[1][type];
			} else {
				return hpNotLucky[1][type];
			}
		} else {
			if(randomLuck < 0.5) {
				return 6;
			} else {
				return 4;
			}
		}
	}
	
	public int getLEVEL() {
		return LEVEL;
	}

	public void setLEVEL(int lEVEL) {
		LEVEL = lEVEL;
	}

	public int getEXP() {
		return EXP;
	}

	public void setEXP(int eXP) {
		EXP = eXP;
	}

	public int getSTRENGTH() {
		return STRENGTH;
	}

	public void setSTRENGTH(int sTRENGTH) {
		STRENGTH = sTRENGTH;
	}

	public int getTECH() {
		return TECH;
	}

	public void setTECH(int tECH) {
		TECH = tECH;
	}

	public int getMAGIC() {
		return MAGIC;
	}

	public void setMAGIC(int mAGIC) {
		MAGIC = mAGIC;
	}

	public int getSPEED() {
		return SPEED;
	}

	public void setSPEED(int sPEED) {
		SPEED = sPEED;
	}

	public int getPROT() {
		return PROT;
	}

	public void setPROT(int pROT) {
		PROT = pROT;
	}

	public int getM_DEF() {
		return M_DEF;
	}

	public void setM_DEF(int m_DEF) {
		M_DEF = m_DEF;
	}
	
	public int getLUCK() {
		return LUCK;
	}

	public void setLUCK(int lUCK) {
		LUCK = lUCK;
	}

	public int getWEP_LVL() {
		return WEP_LVL;
	}

	public void setWEP_LVL(int wEP_LVL) {
		WEP_LVL = wEP_LVL;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getMAX_HP() {
		return MAX_HP;
	}

	public void setMAX_HP(int mAX_HP) {
		MAX_HP = mAX_HP;
	}

	public int getRUNE_1() {
		return RUNE_1;
	}

	public void setRUNE_1(int rUNE_1) {
		RUNE_1 = rUNE_1;
	}

	public int getRUNE_2() {
		return RUNE_2;
	}

	public void setRUNE_2(int rUNE_2) {
		RUNE_2 = rUNE_2;
	}

	public int getRUNE_3() {
		return RUNE_3;
	}

	public void setRUNE_3(int rUNE_3) {
		RUNE_3 = rUNE_3;
	}

	public int getRUNE_4() {
		return RUNE_4;
	}

	public void setRUNE_4(int rUNE_4) {
		RUNE_4 = rUNE_4;
	}

	public int getMAX_RUNE_1() {
		return MAX_RUNE_1;
	}

	public void setMAX_RUNE_1(int mAX_RUNE_1) {
		MAX_RUNE_1 = mAX_RUNE_1;
	}

	public int getMAX_RUNE_2() {
		return MAX_RUNE_2;
	}

	public void setMAX_RUNE_2(int mAX_RUNE_2) {
		MAX_RUNE_2 = mAX_RUNE_2;
	}

	public int getMAX_RUNE_3() {
		return MAX_RUNE_3;
	}

	public void setMAX_RUNE_3(int mAX_RUNE_3) {
		MAX_RUNE_3 = mAX_RUNE_3;
	}

	public int getMAX_RUNE_4() {
		return MAX_RUNE_4;
	}

	public void setMAX_RUNE_4(int mAX_RUNE_4) {
		MAX_RUNE_4 = mAX_RUNE_4;
	}

	public Rune getRightHandRune() {
		return rightHandRune;
	}

	public void setRightHandRune(Rune rightHandRune) {
		this.rightHandRune = rightHandRune;
	}

	public Rune getLeftHandRune() {
		return leftHandRune;
	}

	public void setLeftHandRune(Rune leftHandRune) {
		this.leftHandRune = leftHandRune;
	}

	public Rune getHeadRune() {
		return headRune;
	}

	public void setHeadRune(Rune headRune) {
		this.headRune = headRune;
	}
	
	public boolean hasHeadRune() {
		return headRune != null;
	}
	
	public boolean hasRightHandRune() {
		return rightHandRune != null;
	}
	
	public boolean hasLeftHandRune() {
		return leftHandRune != null;
	}
	
	public String showRemainingRune() {
		return RUNE_1 + "/" + RUNE_2 + "/" + RUNE_3 + "/" + RUNE_4;
	}
	
	
}
