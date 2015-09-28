package dungeon.command;

import dungeon.Dungeon;
import dungeon.item.Item;
import dungeon.room.TreasureRoom;

public class PickCommand extends Command {

	/**
	 * PickCommand resolves the actions happening when the user types the "pick" command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public PickCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		
		
		TreasureRoom room = (TreasureRoom) dungeon.getCurrentRoom();
		Item item = room.getItem();
		
		System.out.println(params);
		
		
		if(params.equals(item.toString())){
			dungeon.getPlayer().addItem(item);
			System.out.println("You picked a " + item.toString() + " !");
			room.setLooted(true);
		}
		
	}

	@Override
	public String description() {
		return "pick <item> : Take the item on the ground.";
	}

}
