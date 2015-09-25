package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.BabyDragon;
import dungeon.entity.Dragon;
import dungeon.entity.Player;

public class DragonRoom extends Room {
	
	private Dragon dragon;
	
	@Override
	public String toString(){
		return "a dragon room";
	}

	@Override
	public String getExtendedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		dragon = new BabyDragon();
		System.out.println("You are in a Dragon room ! The fight begins...");
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.describe);
		return commands;
	}
	
	public Dragon getDragon(){
		return dragon;
	}
	
}
