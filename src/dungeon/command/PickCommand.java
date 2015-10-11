package dungeon.command;

import dungeon.Dungeon;
import dungeon.item.Item;
import dungeon.room.TreasureRoom;

/**
 * The PickCommand class resolves the actions happening when the user types the "pick" command.
 * 
 * @author lucasmouradeoliveira
 *
 */
public class PickCommand extends Command {

	/**
	 * Creates a new item-picking command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public PickCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String params) {
		
		if(params == null){
			System.out.println("Pick what ?");
			return;
		}
		
		
		TreasureRoom room = (TreasureRoom) dungeon.getCurrentRoom();
		Item item = room.getItem();	
		
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
