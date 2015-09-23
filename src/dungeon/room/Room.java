package dungeon.room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dungeon.direction.Path;
import dungeon.direction.PathManager;


public abstract class Room {
	
	protected Map<Path,Room> visibleRooms;
	
	protected Map<Path,Room> hiddenRooms;
	
	protected Path directionOfParent;
		
	public Room(){
		visibleRooms = new HashMap<Path,Room>();
		hiddenRooms = new HashMap<Path,Room>();
	}
	
	/**
	 * 
	 * @return the default description of the room, including the location of the visible adjacent rooms, and the type of the room.
	 */
	public String getDefaultDescription(){
		String s = "You are in " + this.toString() + ".";
		for(Path direction : visibleRooms.keySet()){
			s+="\n"+"There is a room " + direction + ".";
		}	
		return s;
	}
	
	/**
	 * 
	 * @return the advanced description of the room, including the location of the visible adjacent rooms, and clues regarding the location of the hidden ones.
	 */
	public abstract String getExtendedDescription();
	
	/**
	 * Creates a link from this room to an other room.
	 * 
	 * @param direction the direction of the room compared to this room.
	 * 
	 * @param room the room to connect to the current room.
	 */
	public void addRoom(Path direction, Room room){
		
		
		
		if(direction.isVisible()){		
			visibleRooms.put(direction, room);
		}else{
			hiddenRooms.put(direction, room);
		}
		room.directionOfParent = PathManager.getOppositePath(direction);
		room.visibleRooms.put(room.directionOfParent, this);
		
	}
	
	/**
	 * Return the room located in the specified direction.
	 * If there is no room in the specified direction, displays a message in the console and returns the current room.
	 * 
	 * @param direction the direction of the room you want to go compared to this room.
	 * 
	 * @return the room located in the specified direction if it exists, the current room otherwise.
	 */
	public Room go(String direction){
		Room room = getRoom(direction);
		if(room == null){
			System.out.println("Nothing happens");
			return this;
		}
		return room;
	}
	
	/**
	 * Removes all the rooms connected to this room.
	 */
	public void clearAdjacentRooms(){
		visibleRooms.clear();
	}
	
	/**
	 * 
	 * @return the direction from the parent room to this room.
	 */
	public Path getParentDirection(){
		return directionOfParent;
	}
	
	/**
	 * Returns the room connected to this room, according to the specified direction.
	 * 
	 * @param key the direction of the room compared to the current room.
	 * 
	 * @return the room located in the specified direction if it exists, null otherwise.
	 */
	public Room getRoom(String key){
		for(Path p : visibleRooms.keySet()){
			if(p.getDirection().equals(key)){
				return visibleRooms.get(p);
			}
		}
		for(Path p : hiddenRooms.keySet()){
			if(p.getDirection().equals(key)){
				return hiddenRooms.get(p);
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of all the rooms you can access from this room.
	 * 
	 * @return all the adjacent rooms.
	 */
	public List<Room> getAllAdjacentsRooms(){
		List<Room> allRoom = new ArrayList<>();
		for(Room r : visibleRooms.values()){
			allRoom.add(r);
		}
		for(Room r : hiddenRooms.values()){
			allRoom.add(r);
		}
		return allRoom;
	}
	
	public List<Room> getAllVisibleRooms(){
		List<Room> allRoom = new ArrayList<>();
		for(Room r : visibleRooms.values()){
			allRoom.add(r);
		}
		return allRoom;
	}
	
	public List<Room> getAllHiddenRooms(){
		List<Room> allRoom = new ArrayList<>();
		for(Room r : hiddenRooms.values()){
			allRoom.add(r);
		}
		return allRoom;
	}
	
	/**
	 *  Returns a list of all the rooms you can access from this room, 
	 *  with the exception of the parent room of this room.
	 *  
	 * @return a list of adjacent rooms.
	 */
	public List<Room> getAllWithoutGoBack(){
		List<Room> rooms = getAllAdjacentsRooms();
		rooms.remove(visibleRooms.get(directionOfParent));
		return rooms;
	}
	
	public List<Path> getAllRoomDirections(){
		List<Path> paths = new ArrayList<Path>();
		for(Path p : visibleRooms.keySet()){
			paths.add(p);
		}
		for(Path p : hiddenRooms.keySet()){
			paths.add(p);
		}
		return paths;
	}
	
	/**
	 * 
	 * @return the number of hidden rooms connected to this room.
	 */
	public int getNumberOfAdjacentRooms(){
		return getNumberOfVisibleRooms() + getNumberOfHiddenRooms();
	}
	
	/**
	 * 
	 * @return the number of visible rooms connected to this room.
	 */
	public int getNumberOfVisibleRooms(){
		return visibleRooms.size();
	}
	
	/**
	 * 
	 * @return the number of hidden rooms connected to this room.
	 */
	public int getNumberOfHiddenRooms(){
		return hiddenRooms.size();
	}
	
	/**
	 * 
	 * @return true if the room is an exit, false otherwise.
	 */
	public boolean isExit(){
		return this instanceof Exit;
	}

}
