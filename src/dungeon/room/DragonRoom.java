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
			dragon = new BabyDragon(this);
			System.out.println("You are in a dragon room. The fight begins...");
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
		if(dragon.isDead()){
			commands.add(CommandManager.go);
		}else{
			commands.add(CommandManager.run);
		}
		return commands;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public boolean isVisisted(){
		return visited;
	}
	
	public Dragon getDragon(){
		return dragon;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub
		if(visited == false){
			System.out.println("The dragon attack you !");
			dragon.attack(player, dragon.getDegats());
		}
	}
	
}
