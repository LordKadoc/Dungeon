package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.Player;

public class Trap extends Room {
	
	private int damage = 1;
	
	@Override
	public String toString(){
		return "a trap";
	}

	@Override
	public String getExtendedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("Ouch. You take " + damage + " damage.");
		player.takeDamage(damage);
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.help);
		return commands;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		
	}

}
