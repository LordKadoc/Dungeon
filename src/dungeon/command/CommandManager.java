package dungeon.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeon.Dungeon;

/**
 * 
 * The CommandManager class defines the list of all the different commands available in the game, as well as those available in the current room of the dungeon.
 * All the commands currently added to the game are defined as static strings in order to be accessible anywhere in the program.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class CommandManager {
	
	public final static String search = new String("search");
	
	public final static String go = new String("go");
	
	public final static String pick = new String("pick");
	
	public final static String run = new String("run");
	
	public final static String help = new String("help");
	
	public final static String attack = new String("attack");
	
	public final static String use = new String("use");
	
	public final static String inventory = new String("inventory");

	public final static String press = new String("press");
	
	private Map<String,Command> allCommands;
	
	private Map<String,Command> availableCommands;
	
	private Dungeon dungeon;
	
	/**
	 * Creates a new command manager, which determines all the possible commands, and those currently available to the user.
	 * 
	 * @param dungeon the dungeon in which all the commands are being used.
	 */
	public CommandManager(Dungeon dungeon){
		this.dungeon = dungeon;
		this.availableCommands = new HashMap<String,Command>();
		initDefaultCommands();
	}
	
	/**
	 * Initialize a list of all the commands the player can use in the game.
	 */
	private void initDefaultCommands() {
		allCommands = new HashMap<String,Command>();
		allCommands.put(go, new MoveCommand(dungeon));
		allCommands.put(search, new DescriptionCommand(dungeon));
		allCommands.put(pick, new PickCommand(dungeon));
		allCommands.put(run,new RunCommand(dungeon));
		allCommands.put(help, new HelpCommand(dungeon));
		allCommands.put(attack, new AttackCommand(dungeon));
		//allCommands.put("equip",null);
		allCommands.put(use,new UseCommand(dungeon));
		allCommands.put(inventory,new InventoryCommand(dungeon));
		allCommands.put(press,new PressCommand(dungeon));
	}
	
	/**
	 * Returns a command from the list of currently available commands.
	 * 
	 * @param action the name of the command
	 * 
	 * @return the command if it can be found in the list of currently available commands, null otherwise.
	 */
	public Command getAvailableCommand(String action){
		return availableCommands.get(action);
	}
	
	/**
	 * Picks from the list of possible commands all the commands matching the name of the list and adds them to the currently available commands.
	 * 
	 * @param availableActions the names of the commands to search in the global command list.
	 */
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
