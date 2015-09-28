package dungeon.item;
import dungeon.entity.Player;


public abstract class Item {
	
	protected final String name;
	
	/**
	 * Creates a new item and gives it a name.
	 * 
	 * @param name the name of the item.
	 */
	public Item(String name){
		this.name = name;
	}
	
	/**
	 * Resolves the actions happening when a player uses this item.
	 * 
	 * @param player the player using the item.
	 */
	public abstract void use(Player player);
	
	/**
	 * @return the name of the item.
	 */
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}

}
