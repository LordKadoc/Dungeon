package dungeon.direction;

public class Path {
	
	private final String direction;
	
	private final boolean visible;
	
	/**
	 * Creates a new visible path from a room to another.
	 * 
	 * @param direction the direction to go to pass from the first room to the second.
	 */
	public Path(String direction){
		this(direction,true);
	}
	
	/**
	 * 
	 * Creates a new path from a room to another.
	 * 
	 * @param direction the direction to go to pass from the first room to the second.
	 * 
	 * @param visible whether or not the path is visible from the first room.
	 */
	public Path(String direction, boolean visible){
		this.direction = direction;
		this.visible = visible;
	}
	
	/**
	 * 
	 * @return the direction of the path
	 */
	public String getDirection(){
		return direction;
	}
	
	/**
	 * 
	 * @return true if the path to the second room is visible, false otherwise
	 */
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public String toString(){
		return direction;
	}

}
