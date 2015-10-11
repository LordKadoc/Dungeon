package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.Room;

/**
 * The MoveCommand class resolves the actions happening when the user types the "move" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class MoveCommand extends Command {

	/**
	 * Creates a new movement command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public MoveCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		Room room = dungeon.getCurrentRoom().go(params);
		if(room != dungeon.getCurrentRoom()){
			dungeon.enterNewRoom(room);
		}
	}

	@Override
	public String description() {
		return "go <direction> : Makes you move to an adjacent room, if there is a room in the direction you specified.";
	}

}
