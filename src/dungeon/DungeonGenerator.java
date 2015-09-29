package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;
import dungeon.room.TreasureRoom;

public class DungeonGenerator {
	
	private int nbRoomsBeginToEnd;
	
	private Dungeon dungeon;

	
	/**
	 * Creates a new generator for the dungeon.
	 * 
	 * @param nbRoomsBeginToEnd the number of room between the beginning and the end of the dungeon.
	 *
	 * @param dungeon the dungeon to generate.
	 */
	public DungeonGenerator(int nbRoomsBeginToEnd, Dungeon dungeon){
		this.nbRoomsBeginToEnd = nbRoomsBeginToEnd;
		this.dungeon = dungeon;
	}
	
	/**
	 * Generates the main path of the dungeon, a succession of rooms connected one by one.
	 * The main path is always visible and only goes to the 4 cardinal points north, south, west and east.
	 */
	public void generateMainPath(){
		
		Path direction;
		Room currentRoom = dungeon.getCurrentRoom();
		Room tmp;
		
		for(int i = 0; i < nbRoomsBeginToEnd; i++){
			do{
				direction = PathManager.getRandomPath(PathManager.getCardinalPaths());
			}while(direction.equals(currentRoom.getParentDirection()));
			tmp = new SimpleRoom();
			currentRoom.addRoom(direction, tmp);
			currentRoom = tmp;
		}
		do{
			direction = PathManager.getRandomPath(PathManager.getCardinalPaths());
		}while(direction.equals(currentRoom.getParentDirection()));
		currentRoom.addRoom(direction, new Exit());
		
	}
	
	/**
	 *  Generates secondary rooms outside of the main path, some of them being hidden.
	 *  The secondary rooms are connected to the main path and may contain treasures, monsters or traps.
	 */
	public void generateSecondaryPath(){
		
		Stack<Room> path = getPath();
		Room current,tmp;
		Path direction;
		int nb;
		while(!path.isEmpty()){
			nb=0;
			current = path.pop();
			tmp = getRandomRoomType(nb);
			if(tmp != null){
				do{
					direction = PathManager.getRandomPath(PathManager.getAllPaths());
				}while(current.getAllRoomDirections().contains(direction));
				current.addRoom(direction, tmp);
				nb++;
				ArrayList<Room> rooms=generateVoisin(tmp,nb);
				while(!rooms.isEmpty()){
					nb++;
					ArrayList<Room> roomsCurrent=new ArrayList<Room>();
					roomsCurrent.addAll(rooms);
					for(Room r:roomsCurrent)
						rooms.addAll(generateVoisin(r,nb));
					rooms.removeAll(roomsCurrent);
				}
				
			}
		}
	}
	
	public ArrayList<Room> generateVoisin(Room current,int nb){
		int r=new Random().nextInt(PathManager.getAllPaths().size()-2);
		Room room;
		Path direction;
		ArrayList<Room> rooms= new ArrayList<Room>();
		for(int i=0;i<r;i++){
			room= getRandomRoomType(nb);
			if(room != null){
				do{
					direction = PathManager.getRandomPath(PathManager.getAllPaths());
				}while(current.getAllRoomDirections().contains(direction));
				current.addRoom(direction, room);
				rooms.add(room);
			}
		}
		return rooms;
	}
	
	/**
	 * Returns an instance of a room chosen from a list of different types.
	 * @param nb 
	 * 
	 * @return a random type of room, from the following :
	 * 	- simple room
	 * 	- trap room
	 * 	- dragon room
	 * 	- treasure room
	 */
	public Room getRandomRoomType(int nb){
		int random = (int)(Math.random()*100);
		if(random < nb*20){
			return null;
		}
		if(random > 75){
			return new SimpleRoom();
		}else if(random > 55){
			return new Trap();
		}else if(random > 25){
			return new DragonRoom();
		}
		return new TreasureRoom();
	}
	
	/**
	 * 
	 * @return true if the dungeon has a path from the entrance to the exit.
	 */
	public boolean hasPath(){
		return !getPath().isEmpty();
	}
	
	/**
	 * 
	 * @return a pile of rooms, going from the exit of the dungeon to the entrance.
	 */
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
	
	/**
	 * 
	 * @return the dungeon
	 */
	public Dungeon getDungeon(){
		return dungeon;
	}
}
