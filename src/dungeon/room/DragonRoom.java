package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.entity.BabyDragon;
import dungeon.entity.Dragon;
import dungeon.entity.Player;

public class DragonRoom extends Room {
	
	private Dragon dragon;
	private boolean visited = false;
	private boolean premierTour;
	
	@Override
	public String toString(){
		return "a dragon room";
	}

	@Override
	public String getExtendedDescription() {
		String s = "You are in a small room, with scorch marks all over the walls ... It seems like you entered a dragon's nest !!";
		return s;
	}

	@Override
	public void onPlayerEnter(Player player) {
		if(visited == false){
			dragon = new BabyDragon();
			System.out.println("You are in a dragon room. The fight begins...");
			premierTour = true;
		} else {
			System.out.println("You have already kill the dragon");
		}
	}
	
	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.attack);
		commands.add(CommandManager.use);
		commands.add(CommandManager.inventory);
		if(dragon.isDead()){
			commands.add(CommandManager.go);
		}else{
			commands.add(CommandManager.run);
		}
		return commands;
	}
	
	/**
	 * Determines the state of the room, based on whether or not the player already visited it.
	 * 
	 * @param visited true if the player already visited the room, false otherwise.
	 */
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	/**
	 * 
	 * @return whether or not the room has already been visited by the player.
	 */
	public boolean isVisited(){
		return visited;
	}
	
	/**
	 * Returns the dragon living in this room.
	 * 
	 * @return a dragon, which can be alive or dead.
	 */
	public Dragon getDragon(){
		return dragon;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		if(visited == false){
			if(premierTour == false){
				System.out.println("The dragon attacks you !");
				dragon.attack(player, dragon.getDegats());
			} else {
				premierTour = false;
			}
		}
	}
	
}
