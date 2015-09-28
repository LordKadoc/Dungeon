package dungeon.command;

import dungeon.Dungeon;

public class RunCommand extends Command {

	/**
	 * RunCommand resolves the actions happening when the user types the "run" command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public RunCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		dungeon.enterNewRoom(dungeon.getPlayer().getLastVisitedRoom());
	}

	@Override
	public String description() {
		return "run : Get the hell out of here !";
	}

}
