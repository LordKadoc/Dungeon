package dungeon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dungeon.command.Command;
import dungeon.command.CommandManager;
import dungeon.entity.Player;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;

public class Dungeon {
	
	private final Scanner scanner = new Scanner(System.in);
	
	private Room currentRoom;
	
	private Player player;
	
	private CommandManager commandManager;
		
	public Dungeon(Player player){
		this.player = player;
		this.currentRoom = new SimpleRoom();
		this.commandManager = new CommandManager(this);
	}

	public void start(){
		do{
			commandManager.setAvailableCommands(currentRoom.getAvailableCommands());
			System.out.print(getInformations());
			interpreteCommand(scanner.nextLine());
			currentRoom.onTurn(player);
		}while(!gameIsFinished());
		System.out.println(getFinalPrint());
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public String getInformations(){
		if(currentRoom instanceof DragonRoom && !((DragonRoom) currentRoom).isVisited()){
			return player.getInformations() + "\n"
					+ ((DragonRoom) currentRoom).getDragon().getInformations() + "\n"
					+ getQuestion() + "\n"
					+ getPrompt();
		}
		return currentRoom.getDefaultDescription()+"\n"
				+ player.getInformations() + "\n"
				+ getQuestion() + "\n"
				+ getPrompt();
	}
	
	public String getQuestion(){
		return "What do you want to do ?";
	}
	
	public String getFinalPrint(){
		return gameIsWon() ? "You won !" : "You lose !";
	}
	
	public String getPrompt(){
		return "> ";
	}
	
	public void interpreteCommand(String command){
		command.toLowerCase();
		String[] words = command.split(" ");
		int size = words.length;
		if(size >= 1){
			List<String> list = new ArrayList<String>(Arrays.asList(words));
			String action = list.get(0);
			Command c = commandManager.getAvailableCommand(action);
			if(c != null){
				list.remove(0);
				c.act(list.toArray(words));
			}else{
				System.out.println("Nothing happens");
			}
		}
	}
	
	public void enterNewRoom(Room room){
		player.setLastVisitedRoom(currentRoom);
		currentRoom = room;
		currentRoom.onPlayerEnter(player);
	}
	
	public boolean gameIsFinished(){
		return gameIsLost() || gameIsWon();
	}
	
	public boolean gameIsLost(){
		return player.isDead();
	}
	
	public boolean gameIsWon(){
		return currentRoom instanceof Exit;
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	public CommandManager getCommandManager(){
		return commandManager;
	}

}
