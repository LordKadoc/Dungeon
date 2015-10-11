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
	
	/**
	 * Creates a new player, gives him some health and a weapon.
	 * 
	 * @param life the number of hit points of the player.
	 * 
	 * @param weapon the weapon of the player.
	 */
	public Player(int life, Weapon weapon){
		super(life);
		this.weapon = weapon;
		this.inventory = new ArrayList<>();
	}
	
	/**
	 * Adds an item to the inventory of the player.
	 * 
	 * @param i the item to add to the inventory.
	 */
	public void addItem(Item i){
		inventory.add(i);
	}
	
	/**
	 * Removes an item from the inventory of the player.
	 * 
	 * @param i the item to remove from the inventory.
	 */
	public void removeItem(Item i){
		inventory.remove(i);
	}
	
	/**
	 * Returns an item from the inventory of the player.
	 * 
	 * @param index the index of the item in the inventory of the player.
	 * 
	 * @return the item at the specified index if it is correct, null if the index is not valid.
	 */
	public Item getItem(int index){
		if(index < 0 || index >= inventory.size()){
			return null;
		}
		return inventory.get(index);
	}
	
	/**
	 * Returns an item from the inventory of the player.
	 * 
	 * @param name the name of the item.
	 * 
	 * @return the first occurrence of an item in the inventory having the same name as the one specified, null if there is no match.
	 */
	public Item getItem(String name){
		for(Item i : inventory){
			if(i.getName().equals(name)){
				return i;
			}
		}
		return null;
	}
	
	public List<Item> getInventory(){
		return inventory;
	}
	
	/**
	 * Returns the weapon currently equipped by the player, null if there is none.
	 * 
	 * @return the current weapon.
	 */
	public Weapon getWeapon(){
		return weapon;
	}
	
	/**
	 * Sets the new equipped weapon of the player.
	 * 
	 * @param weapon the new weapon of the player.
	 */
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}

	@Override
	public String getInformations() {
		return "Player : you have " + life + " hp.";
	}
	
	/**
	 * Returns the last room in which the player was before the one he is currently visiting.
	 * 
	 * @return the last room visited by the player.
	 */
	public Room getLastVisitedRoom(){
		return lastVisitedRoom;
	}
	
	/**
	 * Sets the last room visited by the player.
	 * 
	 * @param room the last room visited.
	 */
	public void setLastVisitedRoom(Room room){
		this.lastVisitedRoom = room;
	}
	
}