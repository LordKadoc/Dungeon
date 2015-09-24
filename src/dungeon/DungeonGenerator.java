package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;
import dungeon.room.TreasureRoom;

public class DungeonGenerator {
	
	private int nbRoomsBeginToEnd;
	
	private Dungeon dungeon;
	
	public DungeonGenerator(int nbRoomsBeginToEnd, Dungeon dungeon){
		this.nbRoomsBeginToEnd = nbRoomsBeginToEnd;
		this.dungeon = dungeon;
	}
	
	public void generateMainPath(){
		
		//System.out.println("Generate Main Path ...");
		
		Path direction;
		Room currentRoom = dungeon.getCurrentRoom();
		Room tmp;
		
		for(int i = 0; i < nbRoomsBeginToEnd; i++){
			do{
				direction = PathManager.getRandomPath(PathManager.getCardinalPaths());
			}while(direction.equals(currentRoom.getParentDirection()));
			tmp = new SimpleRoom();
			//System.out.println("Add a room to direction : " + direction);
			currentRoom.addRoom(direction, tmp);
			currentRoom = tmp;
		}
		do{
			direction = PathManager.getRandomPath(PathManager.getCardinalPaths());
		}while(direction.equals(currentRoom.getParentDirection()));
		//System.out.println("Add exit to direction : " + direction);
		currentRoom.addRoom(direction, new Exit());
		
		generateSecondaryPath();
		
	}
	
	public void generateSecondaryPath(){
		
		//System.out.println("Generate Second Path ...");
		
		Stack<Room> path = getPath();
		Room current,tmp;
		Path direction;
		
		while(!path.isEmpty()){
			current = path.pop();
			tmp = getRandomRoomType();
			if(tmp != null){
				do{
					direction = PathManager.getRandomPath(PathManager.getAllPaths());
				}while(current.getAllRoomDirections().contains(direction));
			//	System.out.println(current.getDefaultDescription());
			//	System.out.println("Add a room to direction : " + direction);
				current.addRoom(direction, tmp);
			}
		}
	}
	
	public Room getRandomRoomType(){
		int random = (int)(Math.random()*100);
		if(random > 75){
			return new SimpleRoom();
		}else if(random > 55){
			return new Trap();
		}
		return new TreasureRoom();
	}
	
	public boolean hasPath(){
		return !getPath().isEmpty();
	}
	
	public Stack<Room> getPath(){
		Stack<Room> stackRoom = new Stack<>();
		List<Room> listRoom = new ArrayList<>();
		Room room;		
		
		stackRoom.add(getDungeon().getCurrentRoom());
		
		while(!(room = stackRoom.peek()).isExit()){
			List<Room> listTmp = room.getAllWithoutGoBack();
			listTmp.removeAll(listRoom);
			if(listTmp.isEmpty()){
				// Si la pile est vide alors il n'y a pas de sortie
				if(stackRoom.size() <= 1){
					stackRoom.pop();
					return stackRoom;
				}
				else 
					listRoom.add(stackRoom.pop());
			}
			else {
				stackRoom.push(listTmp.get(0));
			}
		}
		return stackRoom;
	}
	
	// ******** GETTERS AND SETTERS *******
	
	public Dungeon getDungeon(){
		return dungeon;
	}
}
