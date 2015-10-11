package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.item.Item;

/**
 * The UseCommand class resolves the actions happening when the user types the "use" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class UseCommand extends Command {

	/**
	 * Creates a new command which handles the use of items in the player's inventory.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public UseCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		
		if(params == null){
			System.out.println("Use what ?");
			return;
		}
		
		Player player = dungeon.getPlayer();
		
		Item i = player.getItem(params);
		if(i != null){
			i.use(player);
			player.removeItem(i);
		}
		
	}

	@Override
	public String description() {
		return "use : Use an item or equip a weapon.";
	}

}
