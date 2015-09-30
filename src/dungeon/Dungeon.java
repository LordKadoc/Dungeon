package dungeon;
import java.util.Scanner;

import dungeon.command.Command;
import dungeon.command.CommandManager;
import dungeon.entity.Player;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.HiddenExit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;

/**
 * 
 * 
 * A dungeon is a series of rooms connected to each other. There is a beginning and an end, and always a path between them. </br>
 * There are different types of rooms in the dungeon, including treasure rooms, monster rooms, traps, ...
 * 
 * The dungeon contains a player who starts in the beginning room.
 * 
 * @author Lucas Moura de Oliveira
 *
 */
public class Dungeon {
	
	private final Scanner scanner = new Scanner(System.in);
	
	private Room currentRoom;
	
	private Player player;
	
	private CommandManager commandManager;
		
	/**
	 * Creates a new dungeon, with a new blank entrance room and a new command manager
	 * 
	 * @param player the character controlled by the user
	 */
	public Dungeon(Player player){
		this.player = player;
		this.currentRoom = new SimpleRoom();
		this.commandManager = new CommandManager(this);
	}

	/**
	 * Starts the main loop of the dungeon, asking an user input at each turn, resolving its actions and displaying their result on the console.
	 * 
	 * The function ends when the player reaches the end of the dungeon or dies.
	 * 
	 */
	public void start(){
		do{
			commandManager.setAvailableCommands(currentRoom.getAvailableCommands());
			System.out.print(getInformations());
			interpreteCommand(scanner.nextLine());
			currentRoom.onTurn(player);
		}while(!gameIsFinished());
	}
	
	/**
	 * 
	 * @return the player in the dungeon
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Returns the status of the game, consisting of : </br>
	 *  - the informations of the room in which the player is </br>
	 *  - a small text to ask the player what does he want to do </br>
	 * 	- a prompt symbol </br>
	 * 
	 * @return the players informations for the current turn
	 */
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
	
	/**
	 * 
	 * @return the question displayed to the user
	 */
	public String getQuestion(){
		return "What do you want to do ?";
	}
	
	/**
	 * 
	 * @return the prompt symbol
	 */
	public String getPrompt(){
		return "> ";
	}
	
	/**
	 * Resolve the action the user entered in the console and plays the turn
	 * 
	 * @param command the user's input, consisting of an action and potential parameters
	 */
	public void interpreteCommand(String command){
		command.toLowerCase();
		String action = Command.getAction(command);
		Command c = commandManager.getAvailableCommand(action);
		if(c != null){
			c.act(Command.getParams(command));
		}else{
			System.out.println("Nothing happens");
		}
	}
	
	/**
	 * Makes the player enter a new room, sets his last visited room, and resolves the action happening when he enters the new room.
	 * @param room
	 */
	public void enterNewRoom(Room room){
		player.setLastVisitedRoom(currentRoom);
		currentRoom = room;
		currentRoom.onPlayerEnter(player);
	}
	
	/**
	 * Returns whether or not the game is finished
	 * 
	 * @return true if the player reached the exit OR if he died, false otherwise
	 */
	public boolean gameIsFinished(){
		return gameIsLost() || gameIsWon();
	}
	
	/**
	 * 
	 * @return true if the player is dead
	 */
	public boolean gameIsLost(){
		return player.isDead();
	}
	
	/**
	 * 
	 * @return true if the player is standing on an exit
	 */
	public boolean gameIsWon(){
		return currentRoom instanceof Exit || currentRoom instanceof HiddenExit && ((HiddenExit)currentRoom).isExited();
	}
	
	/**
	 * 
	 * @return the current room in which the player is
	 */
	public Room getCurrentRoom(){
		return currentRoom;
	}
	
	/**
	 * Returns the command manager of the dungeon. It includes a list of all the commands the player can use
	 * @return the command manager of this dungeon
	 */
	public CommandManager getCommandManager(){
		return commandManager;
	}

}
