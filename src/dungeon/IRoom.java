package dungeon;

import java.util.List;

import dungeon.entity.Player;

public interface IRoom {
	
	/**
	 * Resolves the action when the player enters the room.
	 * 
	 * @param player the player entering the room.
	 */
	public void onPlayerEnter(Player player);	
	
	/**
	 * Returns all the commands available to the player on the current turn.
	 * 
	 * @return a list of currently available commands
	 */
	public List<String> getAvailableCommands();
	
	/**
	 * Resolves the actions happening automatically in the room on every turn.
	 * 
	 * @param player the player in the room.
	 */
	public void onTurn(Player player);

}
