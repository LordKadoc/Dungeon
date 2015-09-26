package dungeon.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeon.Dungeon;

public class CommandManager {
	
	public final static String search = new String("search");
	
	public final static String go = new String("go");
	
	public final static String pick = new String("pick");
	
	public final static String run = new String("run");
	
	public final static String help = new String("help");
	
	private Map<String,Command> allCommands;
	
	private Map<String,Command> availableCommands;
	
	private Dungeon dungeon;
	
	public CommandManager(Dungeon dungeon){
		this.dungeon = dungeon;
		this.availableCommands = new HashMap<String,Command>();
		initDefaultCommands();
	}
	
	private void initDefaultCommands() {
		allCommands = new HashMap<String,Command>();
		allCommands.put(go, new MoveCommand(dungeon));
		allCommands.put(search, new DescriptionCommand(dungeon));
		allCommands.put(pick, new PickCommand(dungeon));
		allCommands.put(run,new RunCommand(dungeon));
		allCommands.put(help, new HelpCommand(dungeon));
		//allCommands.put("attack",null);
		//allCommands.put("equip",null);
		//allCommands.put("use",null);
	}
	
	public void addCommand(String action, Command command){
		allCommands.put(action,command);
	}

	public Command getAvailableCommand(String action){
		return availableCommands.get(action);
	}
	
	public void setAvailableCommands(List<String> availableActions){
		Command command;
		availableCommands.clear();
		for(String s : availableActions){
			command = allCommands.get(s);
			if(command != null){
				availableCommands.put(s, command);
			}
		}
	}

}
