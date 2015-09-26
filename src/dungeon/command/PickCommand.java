package dungeon.command;

import dungeon.Dungeon;
import dungeon.entity.Weapon;
import dungeon.room.TreasureRoom;

public class PickCommand extends Command {

	public PickCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		
		
		String item="";
		TreasureRoom room = (TreasureRoom) dungeon.getCurrentRoom();
		Weapon weapon = room.getWeapon();
		
		if(weapon == null){
			return;
		}
		
		for(String s : parameters){
			if(s != null){
				item+=s+" ";
			}
		}
		item = item.substring(0,item.length()-1);
		
		if(item.equals(weapon.toString())){
			dungeon.getPlayer().setWeapon(weapon);
			System.out.println("You picked a " + weapon + " !");
			room.setLooted(true);
		}
		
	}

	@Override
	public String description() {
		return "pick <item> : Take the item on the ground.";
	}

}
