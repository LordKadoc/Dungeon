package dungeon.room;

import dungeon.entity.Player;

public class Exit extends Room {
	
	@Override
	public String toString(){
		return "an exit";
	}

	@Override
	public String getExtendedDescription() {
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("You finally escaped the dungeon !");
	}

}
