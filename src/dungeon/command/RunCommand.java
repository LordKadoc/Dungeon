package dungeon.command;

import dungeon.Dungeon;

public class RunCommand extends Command {

	public RunCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		dungeon.enterNewRoom(dungeon.getPlayer().getLastVisitedRoom());
	}

	@Override
	public String description() {
		return "run : Get the hell out of here !";
	}

}
