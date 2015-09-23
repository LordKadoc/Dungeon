package dungeon.command;

import java.util.HashMap;
import java.util.Map;

import dungeon.Dungeon;

public class CommandManager {
	
	private Map<String,Command> availableCommands;
	
	private Dungeon dungeon;
	
	public CommandManager(Dungeon dungeon){
		this.dungeon = dungeon;
		initDefaultCommands();
	}
	
	private void initDefaultCommands() {
		availableCommands = new HashMap<String,Command>();
		availableCommands.put("go", new MoveCommand(dungeon));
		availableCommands.put("describe", new DescriptionCommand(dungeon));
	}

	public Command getCommand(String action){
		return availableCommands.get(action);
	}
	
	public void addCommand(String action, Command command){
		availableCommands.put(action, command);
	}
	
	public void removeCommand(String action){
		availableCommands.remove(action);
	}

}
