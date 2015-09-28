package dungeon.command;

import dungeon.Dungeon;
import dungeon.item.Item;
import dungeon.room.TreasureRoom;

public class PickCommand extends Command {

	public PickCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		
		
		String item="";
		TreasureRoom room = (TreasureRoom) dungeon.getCurrentRoom();
		Item i = room.getItem();
		
		if(parameters.length < 2){
			System.out.println("Nothing happens");
			return;
		}
		
		for(String s : parameters){
			if(s != null){
				item+=s+" ";
			}
		}
		item = item.substring(0,item.length()-1);
		
		if(item.equals(i.toString())){
			dungeon.getPlayer().addItem(i);
			System.out.println("You picked a " + i.toString() + " !");
			room.setLooted(true);
		}
		
	}

	@Override
	public String description() {
		return "pick <item> : Take the item on the ground.";
	}

}
