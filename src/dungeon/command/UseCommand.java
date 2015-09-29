package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.item.Item;

public class UseCommand extends Command {

	/**
	 * UseCommand resolves the actions happening when the user types the "use" command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public UseCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		Player player = dungeon.getPlayer();
		
		Item i = player.getItem(params);
		Item iNow = player.getWeapon();
		if(i != null){
			i.use(player);
			player.removeItem(i);
			player.addItem(iNow);			
		}
		
	}

	@Override
	public String description() {
		return "use : Use an item or equip a weapon.";
	}

}
