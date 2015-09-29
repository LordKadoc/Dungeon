package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.item.Item;

public class InventoryCommand extends Command {

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
