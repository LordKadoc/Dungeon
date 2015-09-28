package dungeon.item;
import dungeon.entity.Player;


public abstract class Item {
	
	protected final String name;
	
	public Item(String name){
		this.name = name;
	}
	
	public abstract void use(Player player);
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
