package dungeon.command;

import dungeon.Dungeon;

public class DescriptionCommand extends Command {

	public DescriptionCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		System.out.println("===== You are looking at the room ... =====" + "\n"
						 + dungeon.getCurrentRoom().getExtendedDescription() + "\n"
						 + "===========================================");
	}

}
