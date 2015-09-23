package dungeon.room;

import dungeon.direction.Path;
import dungeon.entity.Player;

public class SimpleRoom extends Room {
	
	@Override
	public String toString(){
		return "a simple room";
	}

	@Override
	public String getExtendedDescription() {
		String s = getDefaultDescription();
		for(Path p : hiddenRooms.keySet()){
			s+="\n"+"There is a room " + p + ".";
		}
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		// TODO Auto-generated method stub
		
	}
	
}
