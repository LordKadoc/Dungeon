package dungeon.command;

import dungeon.Dungeon;

public class HelpCommand extends Command {

	/**
	 * HelpCommand resolves the actions happening when the user types the "help" command.
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