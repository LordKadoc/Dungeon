package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.Room;

public class MoveCommand extends Command {

	public MoveCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		Room room = dungeon.getCurrentRoom().go(parameters[0]);
		if(room != dungeon.getCurrentRoom()){
			dungeon.enterNewRoom(room);
		}
	}

}
