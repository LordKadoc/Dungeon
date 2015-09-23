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
		
	}
	
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
	
	public static List<Path> getCardinalPaths(){
		List<Path> paths = new ArrayList<Path>();
		for(Path p : oppositeDirections.keySet()){
			if(p.isVisible()){
				paths.add(p);
			}
		}
		return paths;
	}
	
	public static List<Path> getHiddenPaths(){
		List<Path> paths = new ArrayList<Path>();
		for(Path p : oppositeDirections.keySet()){
			if(!p.isVisible()){
				paths.add(p);
			}
		}
		return paths;
	}
	
	public static List<Path> getAllPaths(){
		List<Path> paths = getCardinalPaths();
		paths.addAll(getHiddenPaths());
		return paths;
	}
	
	public static Path getRandomPath(List<Path> paths){
		return paths.get((int)(Math.random()*paths.size()));
	}

}
