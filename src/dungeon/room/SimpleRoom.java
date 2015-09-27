package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.direction.Path;
import dungeon.entity.Player;

public class SimpleRoom extends Room {
	
	@Override
	public String toString(){
		return "a simple room";
	}

	@Override
	public String getExtendedDescription() {
		if(hiddenRooms.isEmpty()){
			return "You are in an empty room. You can see few pieces of furniture around you, but nothing unusual ...";
		}
		String s = "";	
		for(Path p : hiddenRooms.keySet()){
			s+="\n"+"You found a room " + p + " !";
		}
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.go);
		return commands;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
