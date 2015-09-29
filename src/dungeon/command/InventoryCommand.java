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
		System.out.println("Player inventory : ");
		Player player = dungeon.getPlayer();
		for(Item i : player.getInventory()){
			System.out.println(" - " + i);
		}
	}

	@Override
	public String description() {
		return "inventory : Displays the inventory of the player.";
	}

}
