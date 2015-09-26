package dungeon.command;

import dungeon.Dungeon;

public class DescriptionCommand extends Command {

	public DescriptionCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		System.out.println("===== You are searching the room ... =====" + "\n"
						 + dungeon.getCurrentRoom().getExtendedDescription() + "\n"
						 + "==========================================");
	}

	@Override
	public String description() {
		return "search : Describes the current room, and reveals any hidden entrances.";
	}

}
