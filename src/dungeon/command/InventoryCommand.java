package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.item.Item;

/**
 * The InventoryCommand class resolves the actions happening when the user types the "inventory" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class InventoryCommand extends Command {

	/**
	 * Creates a new inventory displaying command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public InventoryCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		System.out.println("\nPlayer inventory : ");
		Player player = dungeon.getPlayer();
		if(player.getInventory().isEmpty()){
			System.out.println(" - nothing\n");
			return;
		}
		for(Item i : player.getInventory()){
			System.out.println(" - " + i);
		}
		System.out.println();
	}

	@Override
	public String description() {
		return "inventory : Displays the inventory of the player.";
	}

}
