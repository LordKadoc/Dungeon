package dungeon.entity;

import java.util.HashMap;
import java.util.Map;

import dungeon.room.Room;

public class Player extends Entity {
	
	
	private Room lastVisitedRoom;
	
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

	@Override
	public String getInformations() {
		return "Player : you have " + life + " hp.";
	}
	
	public Room getLastVisitedRoom(){
		return lastVisitedRoom;
	}
	
	public void setLastVisitedRoom(Room room){
		this.lastVisitedRoom = room;
	}
	
}