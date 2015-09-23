package dungeon.room;

import dungeon.entity.Player;

public class Trap extends Room {
	
	private int damage = 1;
	
	@Override
	public String toString(){
		return "a trap";
	}

	@Override
	public String getExtendedDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onPlayerEnter(Player player) {
		System.out.println("Ouch. You take " + damage + " damage.");
		player.takeDamage(damage);
	}

}
