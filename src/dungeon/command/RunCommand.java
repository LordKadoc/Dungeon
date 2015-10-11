package dungeon.command;

import dungeon.Dungeon;

/**
 * The RunCommand class resolves the actions happening when the user types the "run" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class RunCommand extends Command {

	/**
	 * Creates a new run command.
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
