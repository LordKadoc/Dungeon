package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.Room;

public class MoveCommand extends Command {

	public MoveCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		
		StringBuilder builder = new StringBuilder();
		for(String s : parameters){
			if(s != null)
				builder.append(s+" ");
		}
		
		Room room = dungeon.getCurrentRoom().go(builder.toString().trim());
		if(room != dungeon.getCurrentRoom()){
			dungeon.enterNewRoom(room);
		}
	}

}
