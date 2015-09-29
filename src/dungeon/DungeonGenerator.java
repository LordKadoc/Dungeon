package dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.HiddenExit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;
import dungeon.room.TreasureRoom;

public class DungeonGenerator {
	
	private int nbRoomsBeginToEnd;
	
	private Dungeon dungeon;
	
	private int currentLvl;

	
	/**
	 * Creates a new generator for the dungeon.
	 * 
	 * @param nbRoomsBeginToEnd the number of room between the beginning and the end of the dungeon.
	 *
	 * @param dungeon the dungeon to generate.
	 */
	public DungeonGenerator(int nbRoomsBeginToEnd, Dungeon dungeon, int currentLvl){
		this.nbRoomsBeginToEnd = nbRoomsBeginToEnd;
		this.dungeon = dungeon;
		this.currentLvl = currentLvl;
	}
	
	/**
	 * Generates the main path of the dungeon, a succession of rooms connected one by one.
	 * The main path is always visible and only goes to the 4 cardinal points North, South, West and East.
	 */
	public void generateMainPath(){
		
		Path direction;
		Room currentRoom = dungeon.getCurrentRoom();
		Room tmp;
		
		for(int i = 0; i < nbRoomsBeginToEnd; i++){
			do{
				direction = PathManager.getRandomPath(PathManager.getCardinalPaths());
			}while(direction.equals(currentRoom.getParentDirection()));
			tmp = (int)(Math.random() * 100) > 75 ? new DragonRoom(currentLvl) : new SimpleRoom();
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
				ArrayList<Room> rooms=generateNeighbors(tmp,nb);
				while(!rooms.isEmpty()){
					nb++;
					ArrayList<Room> roomsCurrent=new ArrayList<Room>();
					for(int i=rooms.size()-1;i>=0;i--){
						if(rooms.get(i) instanceof Trap)
							rooms.remove(i);
					}
					roomsCurrent.addAll(rooms);
					for(Room r:roomsCurrent)
						rooms.addAll(generateNeighbors(r,nb));
					rooms.removeAll(roomsCurrent);
				}
				
			}
		}
	}
	
	/**
	 * Generates a certain amount of adjacent rooms to a room, in random direction, and link them to it.
	 * 
	 * @param current the room from which to generate adjacent rooms.
	 * 
	 * @param nb the depth of the room, relative to its path.
	 * 
	 * @return the list of generated adjacent rooms.
	 */
	public ArrayList<Room> generateNeighbors(Room current,int nb){
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
	 * The random room can be null, which allows the secondary path generation algorithm to have an end.
	 * 
	 * @param nb the depth of the room you want to generate relative to the beginning of its path.
	 * 
	 * @return a random type of room, from the following :
	 * 	- simple room
	 * 	- trap room
	 * 	- dragon room
	 * 	- treasure room
	 * 	- none
	 */
	public Room getRandomRoomType(int nb){
		int random = (int)(Math.random()*100);
		if(random < nb*20){
			return null;
		}
		if(random > 90)
			return new HiddenExit();
		else if(random > 80)
			return new SimpleRoom();
		else if(random > 60)
			return new Trap();
		else if(random > 40)
			return new DragonRoom(currentLvl);
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
	
	/**
	 * 
	 * @return the dungeon
	 */
	public Dungeon getDungeon(){
		return dungeon;
	}
}
