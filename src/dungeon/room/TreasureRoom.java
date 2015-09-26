package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.Player;
import dungeon.entity.Weapon;
import dungeon.entity.WoodenBow;

public class TreasureRoom extends Room {
	
	private Weapon weapon;
	
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
			s+=" - a " + weapon.toString() + " on the ground, just in front of you.";
		}
		
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		if(weapon == null && !looted){
			weapon = new WoodenBow();
		}
		System.out.println("You enter a treasure room !");
		System.out.println("You see a " + weapon + " in front of you.");
	}

	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.go);
		commands.add(CommandManager.pick);
		return commands;
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setLooted(boolean looted){
		this.looted = looted;
	}
	
	public boolean isLooted(){
		return looted;
	}

}
