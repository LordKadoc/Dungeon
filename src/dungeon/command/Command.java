package dungeon.command;

import dungeon.Dungeon;

public abstract class Command {
	
	protected Dungeon dungeon;
	
	public Command(Dungeon dungeon){
		this.dungeon = dungeon;
	}
	
	public abstract void act(String ...parameters);
	
	public abstract String description();
	
}
