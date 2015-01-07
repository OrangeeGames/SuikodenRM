package entities;

import java.util.ArrayList;

public class Inventory {
	
	public ArrayList<Item> inventoryList;
	public int potch;
	
	public Inventory() {
		inventoryList = new ArrayList<Item>();
		potch = 0;
	}
	
	public Inventory(ArrayList<Item> inventoryList) {
		this.inventoryList = inventoryList;
		this.potch = 0;
	}
	
	public boolean addItem(Item i) {
		if(inventoryList.size() < 30) {
			inventoryList.add(i);
			return true;
		} else {
			return false;
		}
	}
	
	public Item getItem(int i) {
		return inventoryList.get(i);
	}
	
	public void addPotch(int i) {
		this.potch += i;
	}
	
	public void deducePotch(int i) {
		this.potch -= i;
	}
	
	public int getPotch() {
		return potch;
	}
}
