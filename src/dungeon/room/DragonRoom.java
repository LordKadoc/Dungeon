package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.Player;

public class DragonRoom extends Room {

	@Override
	public String getExtendedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		// TODO Auto-generated method stub
		System.out.println("You are in a Dragon room ! The fight begin...");
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.describe);
		commands.add(CommandManager.go);
		return commands;
	}
	
}
