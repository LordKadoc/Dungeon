package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.Player;
import dungeon.item.Item;
import dungeon.item.Potion;
import dungeon.item.WoodenBow;

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
		
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("You enter a treasure room !");
		if(item == null && !looted){
			if((int)(Math.random()*100) > 50){
				item = new WoodenBow();
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
		if(!looted){
			commands.add(CommandManager.pick);	
		}
		return commands;
	}
	
	public Item getItem(){
		return item;
	}
	
	public void setLooted(boolean looted){
		this.looted = looted;
	}
	
	public boolean isLooted(){
		return looted;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		
	}

}
