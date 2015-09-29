package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.HiddenExit;

public class PressCommand extends Command {

	public PressCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		HiddenExit exit = (HiddenExit) dungeon.getCurrentRoom();
		if(params.equals(exit.getMechanism())){
			exit.exit();
			System.out.println("You found an hidden exit to the dungeon !");
		}else{
			System.out.println("Nothing happens");
		}
	}

	@Override
	public String description() {
		return "press : Triggers the hidden mechanism";
	}

}
