package dungeon.room;

public class SimpleRoom extends Room {
	
	@Override
	public String toString(){
		return "a simple room";
	}

	@Override
	public String getExtendedDescription() {
		return getDefaultDescription() + hiddenRooms;
	}
	
}
