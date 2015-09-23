package dungeon;
import java.util.Scanner;

import dungeon.entity.Player;
import dungeon.entity.WoodenSword;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;

public class Dungeon {
	
	private Room currentRoom;
	
	private final Scanner scanner = new Scanner(System.in);
	
	private Player player;
	
	public Dungeon(Player player){
		initDungeon();
		this.player = player;
	}
	
	private void initDungeon() {
		currentRoom = new SimpleRoom();
	}

	public void start(){
		
		System.out.println("********** Welcome in Dragon's Dungeon !!! **********");
		
		do{
			System.out.print(getInformations());
			interpreteCommand(scanner.nextLine());
		}while(!gameIsFinished());
		System.out.println(getFinalPrint());
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
		if(size >= 2){
			String action = words[0];
			String param = command.substring(action.length()+1, command.length());
			if(action.equals("go")){
				enterNewRoom(currentRoom.go(param));
			}else{
				System.out.println("I don't understand what you mean");
			}
		}else{
			if(command.equals("describe")){
				System.out.println(currentRoom.getExtendedDescription());
			}
			
		}
	}
	
	public void enterNewRoom(Room room){
		if(room.equals(currentRoom)){
			return;
		}
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
		Dungeon d = new Dungeon(new Player(10, new WoodenSword()));
		new DungeonGenerator(4, d).generateMainPath();
		d.start();
	}

}
