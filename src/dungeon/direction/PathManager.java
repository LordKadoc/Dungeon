package dungeon.direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathManager {
	
	private static Map<Path,Path> oppositeDirections = new HashMap<Path,Path>();
	
	public static Path north = new Path("north");
	public static Path south = new Path("south");
	public static Path west = new Path("west");
	public static Path east = new Path("east");
	public static Path back = new Path("back");

	
	static
	{
		oppositeDirections.put(north, south);
		oppositeDirections.put(south, north);
		oppositeDirections.put(west, east);
		oppositeDirections.put(east, west);
		oppositeDirections.put(new Path("under the carpet",false),new Path("hover the hatch"));
		oppositeDirections.put(new Path("behind the painting",false), new Path("back"));
		
	}
	
	/**
	 * Returns the opposite direction of the path.
	 * 
	 * @param path the path you want to get the opposite of.
	 * @return the opposite of the path if it is referenced, 
	 * 	false if the path entered is null,
	 * 	or a default path if there is no match in the list of opposites.
	 */
	public static Path getOppositePath(Path path){
		if(path == null){
			return null;
		}
		Path p = oppositeDirections.get(path);
		if(p == null){
			return back;
		}
		return p;
	}
	
	/**
	 * Returns a list of visible paths, one for every cardinal point : North, South, West and East.
	 * 
	 * @return the list of visible paths.
	 */
	public static List<Path> getCardinalPaths(){
		List<Path> paths = new ArrayList<Path>();
		for(Path p : oppositeDirections.keySet()){
			if(p.isVisible()){
				paths.add(p);
			}
		}
		return paths;
	}
	
	/**
	 * Returns a list containing all the possible hidden paths, not directly visible by the player.
	 * 
	 * @return the list of hidden paths.
	 */
	public static List<Path> getHiddenPaths(){
		List<Path> paths = new ArrayList<Path>();
		for(Path p : oppositeDirections.keySet()){
			if(!p.isVisible()){
				paths.add(p);
			}
		}
		return paths;
	}
	
	/**
	 * Returns a list containing all the possible paths, whether or not they are visible.
	 * 
	 * @return the list of all the paths.
	 */
	public static List<Path> getAllPaths(){
		List<Path> paths = getCardinalPaths();
		paths.addAll(getHiddenPaths());
		return paths;
	}
	
	/**
	 * Returns a random value from a list of paths.
	 * 
	 * @param paths the list of paths from which you want to randomly select a value.
	 * 
	 * @return a random path from a list.
	 */
	public static Path getRandomPath(List<Path> paths){
		return paths.get((int)(Math.random()*paths.size()));
	}

}
