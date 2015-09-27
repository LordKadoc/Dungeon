package dungeon.command;

import dungeon.Dungeon;
import dungeon.room.DragonRoom;

public class AttackCommand extends Command {

	public AttackCommand(Dungeon dungeon) {
		super(dungeon);
	}

	@Override
	public void act(String... parameters) {
		System.out.println("You attack the dragon");
		dungeon.getPlayer().attack(((DragonRoom) dungeon.getCurrentRoom()).getDragon(), dungeon.getPlayer().getWeapon().getDamage());
		if(((DragonRoom) dungeon.getCurrentRoom()).getDragon().isDead()){
			System.out.println("The dragon is dead");
			((DragonRoom) dungeon.getCurrentRoom()).setVisited(true);
		}
	}

	@Override
	public String description() {
		return "Attack : attack the dragon in dragon's room";
	}

}
