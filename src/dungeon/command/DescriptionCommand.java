package dungeon.command;

import dungeon.Dungeon;

public class DescriptionCommand extends Command {

	/**
	 * DescriptionCommand resolves the actions happening when the user types the "describe" command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public DescriptionCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		System.out.println("===== You are searching the room ... =====" + "\n"
						 + dungeon.getCurrentRoom().getExtendedDescription() + "\n"
						 + "==========================================");
	}

	@Override
	public String description() {
		return "search : Describes the current room, and reveals any hidden entrances.";
	}

}
