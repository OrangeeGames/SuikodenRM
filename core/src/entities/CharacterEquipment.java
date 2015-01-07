package entities;

public class CharacterEquipment {
	
	public Item headGear;
	public Item bodyGear;
	public Item shield;
	
	public Item[] accessories = {null, null, null};
	
	public int type;
	
	public CharacterEquipment (int type) {
		this.type = type;
		headGear = null;
		bodyGear = null;
		shield = null;
	}
	
	public boolean canUseShield() {
		return (this.type & Item.SHIELD) != 0;
	}
	
	public Item removeHeadGear() {
		Item eqReturn = headGear;
		this.headGear = null;
		return eqReturn;
	}
	
	public void addHeadGear(Item newHeadGear) {
		headGear = newHeadGear;
	}
	
	public Item removeBodyGear() {
		Item eqReturn = bodyGear;
		this.bodyGear = null;
		return eqReturn;
	}
	
	public void addBodyGear(Item newBodyGear) {
		this.bodyGear = newBodyGear;
	}
	
	public Item removeShield() {
		Item eqReturn = shield;
		this.shield = null;
		return eqReturn;
	}
	
	public void addShield(Item newShield) {
		this.shield = newShield;
	}
	
	public Item removeAccessory(int i) {
		Item itemReturn = accessories[i];
		accessories[i] = null;
		return itemReturn;
	}
	
	public void addAccessory(int i, Item item) {
		accessories[i] = item;
	}
	
	public boolean compatible(Item i) {
		return (i.getType() & type) != 0;
	}
	
	public Item getHeadGear() {
		return headGear;
	}
	
	public Item getBodyGear() {
		return bodyGear;
	}
	
	public Item getShield() {
		return shield;
	}
	
	public Item getAccessory(int i) {
		return accessories[i];
	}
}

