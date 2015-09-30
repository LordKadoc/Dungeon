package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.direction.Path;
import dungeon.entity.Player;

public class Trap extends Room {
	
	private int damage = 1;
	
	private boolean activated = false;
	
	@Override
	public String toString(){
		return "a trap";
	}

	@Override
	public String getExtendedDescription() {
		String s = "It's a trap ! And you stepped on it ! Ahah";
		for(Path p : hiddenRooms.keySet()){
			s+="\n"+"You found a room " + p + " !";
		}
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		if(!activated){
			System.out.println("Ouch. You take " + damage + " damage.");
			player.takeDamage(damage);
			activated = true;
		}else{
			System.out.println("You already activated this trap ...");
		}
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.help);
		commands.add(CommandManager.inventory);
		commands.add(CommandManager.search);
		return commands;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		
	}

}
