package dungeon.room;

import dungeon.entity.Player;

public class TreasureRoom extends Room {
	
	@Override
	public String toString(){
		return "a treasure room";
	}

	@Override
	public String getExtendedDescription() {
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		// TODO Auto-generated method stub
		
	}

}
