package dungeon;

import java.util.List;

import dungeon.entity.Player;

public interface IRoom {
	
	public void onPlayerEnter(Player player);	
	
	public List<String> getAvailableCommands();
	
	public void onTurn(Player player);

}
