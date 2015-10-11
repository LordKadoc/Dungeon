package dungeon.command;

import dungeon.Dungeon;

/**
 * The HelpCommand class resolves the actions happening when the user types the "help" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class HelpCommand extends Command {

	/**
	 * Creates a new help command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public HelpCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		Command c;
		System.out.println("Available commands : \n");
		for(String s : dungeon.getCurrentRoom().getAvailableCommands()){
			c = dungeon.getCommandManager().getAvailableCommand(s);
			if(c != null){
				System.out.println(" > " + c.description());
			}
		}
		System.out.println();
	}

	@Override
	public String description() {
		return "help : Displays all the currently available commands.";
	}

}
