package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.direction.Path;
import dungeon.entity.Player;
import dungeon.item.Item;
import dungeon.item.Potion;
import dungeon.item.Weapon;

public class TreasureRoom extends Room {
	
	private Item item;
	
	private boolean looted = false;
	
	@Override
	public String toString(){
		return "a treasure room";
	}

	@Override
	public String getExtendedDescription() {
		String s =  "You are in a treasure room. Many explorers already looted it, and it's almost empty now ... There are still a few items on the ground though ...\n";
		if(looted){
			s+=" - nothing useful";
		}else{
			s+=" - a " + item.toString() + " on the ground, just in front of you.";
		}
		for(Path p : hiddenRooms.keySet()){
			s+="\n"+"You found a room " + p + " !";
		}
		
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("You enter a treasure room !");
		if(item == null && !looted){
			if((int)(Math.random()*100) > 50){
				if((int)(Math.random()*100) > 25){
					item = new Weapon("Wooden Bow", 2);
				} else {
					item = new Weapon("Flame Sword", 4);
				}
			}else{
				item = new Potion();
			}
			System.out.println("You see a " + item.toString() + " in front of you.");
		}
	}

	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.inventory);
		if(!looted){
			commands.add(CommandManager.pick);	
		}
		return commands;
	}
	
	/**
	 * Returns the item the player can pick up in this room.
	 * The item can be a potion or a weapon.
	 * 
	 * @return a treasure item.
	 */
	public Item getItem(){
		return item;
	}
	
	/**
	 * Sets the new state of the treasure room : looted or not.
	 * 
	 * @param looted true if the treasure in the room has already been salvaged, false otherwise. 
	 */
	public void setLooted(boolean looted){
		this.looted = looted;
	}
	
	/**
	 * 
	 * @return true if the room has been looted, false otherwise.
	 */
	public boolean isLooted(){
		return looted;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		
	}

}
