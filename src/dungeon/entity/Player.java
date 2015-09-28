package dungeon.entity;

import java.util.List;
import java.util.ArrayList;

import dungeon.item.Item;
import dungeon.item.Weapon;
import dungeon.room.Room;

public class Player extends Entity {
	
	
	private Room lastVisitedRoom;
	
	private Weapon weapon;
	private List<Item> inventory;
	
	public Player(int life, Weapon weapon){
		super(life);
		this.weapon = weapon;
		this.inventory = new ArrayList<>();
	}
	
	public void addItem(Item i){
		inventory.add(i);
	}
	
	public void removeItem(Item i){
		inventory.remove(i);
	}
	
	public Item getItem(int index){
		return inventory.get(index);
	}
	
	public Item getItem(String name){
		for(Item i : inventory){
			if(i.getName().equals(name)){
				return i;
			}
		}
		return null;
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