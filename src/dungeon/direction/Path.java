package dungeon.direction;

public class Path {
	
	private final String direction;
	
	private final boolean visible;
	
	public Path(String direction){
		this(direction,true);
	}
	
	public Path(String direction, boolean visible){
		this.direction = direction;
		this.visible = visible;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public String toString(){
		return direction;
	}

}
