package dungeon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dungeon.command.Command;
import dungeon.command.CommandManager;
import dungeon.entity.Player;
import dungeon.entity.WoodenSword;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;

public class Dungeon {
	
	private final Scanner scanner = new Scanner(System.in);
	
	private Room currentRoom;
	
	private Player player;
	
	private CommandManager commandManager;
	
	private int level;
	
	public Dungeon(Player player){
		initDungeon();
		this.player = player;
	}
	
	private void initDungeon() {
		currentRoom = new SimpleRoom();
		commandManager = new CommandManager(this);
		level = 1;
	}

	public void start(){
		
		System.out.println("********** Welcome in the Dragon's Cave !!! **********");
		
		while(level<=5){
			
			currentRoom = new SimpleRoom();
			new DungeonGenerator(5+2*level, this).generateMainPath();
			System.out.println("You enter level " + level + " of the dungeon !");
			do{
				if(currentRoom instanceof DragonRoom){
					//Code a ecrire...
				} else {
					System.out.print(getInformations());
					interpreteCommand(scanner.nextLine());
				}
			}while(!gameIsFinished());
			System.out.println(getFinalPrint());
			level++;
		}
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public String getInformations(){
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
			Command c = commandManager.getCommand(action);
			if(c != null){
				list.remove(0);
				c.act(list.toArray(words));
			}
		}
	}
	
	public void enterNewRoom(Room room){
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

	public static void main(String[] args) {
		new Dungeon(new Player(10,new WoodenSword())).start();
	}

}
