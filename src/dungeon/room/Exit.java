package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.entity.Player;

public class Exit extends Room {
	
	@Override
	public String toString(){
		return "an exit";
	}

	@Override
	public String getExtendedDescription() {
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("You reached the exit !");
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		return commands;
	}

}
