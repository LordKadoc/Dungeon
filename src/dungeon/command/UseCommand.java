package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Player;
import dungeon.item.Item;

public class UseCommand extends Command {

	public UseCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		Player player = dungeon.getPlayer();
		
		String item = "";
		
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
		
		Item i = player.getItem(item);
		if(i != null){
			i.use(player);
		}
		
	}

	@Override
	public String description() {
		return "use : use an item or equip a weapon";
	}

}
