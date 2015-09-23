package dungeon.entity;

import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {
	
	private int life;
	private Weapon weapon;
	private Map<String, Object> inventory;
	
	public Player(int life, Weapon weapon){
		super(life);
		this.weapon = weapon;
		this.inventory = new HashMap<>();
	}
	
	public void setInventory(String s, Object o){
		inventory.put(s, o);
	}
	
	public Object getInInventory(String s){
		return inventory.get(s);
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
}