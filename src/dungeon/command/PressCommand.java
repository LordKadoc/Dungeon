package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.HiddenExit;

/**
 * The PresCommand class resolves the actions happening when the user types the "press" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class PressCommand extends Command {

	/**
	 * Creates a new button-pressing command
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public PressCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		
		if(params == null){
			System.out.println("Press what ?");
			return;
		}
		
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
		return "press : Triggers the hidden mechanism.";
	}

}
