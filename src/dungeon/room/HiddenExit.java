package dungeon.room;

import java.util.ArrayList;
import java.util.List;

import dungeon.command.CommandManager;
import dungeon.direction.Path;
import dungeon.entity.Player;

public class HiddenExit extends Room {
	
	private boolean exited;
	
	private String exitMechanism;
	
	@Override
	public String toString(){
		return "a simple room";
	}

	@Override
	public void onPlayerEnter(Player player) {
		if(this.exitMechanism == null){
			this.exitMechanism = (int)(Math.random() * 2) > 0 ? "button": "switch";
		}
	}

	@Override
	public List<String> getAvailableCommands() {
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.inventory);
		commands.add(CommandManager.press);
		return commands;
	}

	@Override
	public void onTurn(Player player) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getExtendedDescription() {
		String s = "";
		if(hiddenRooms.isEmpty()){
			s+="You are in an empty room. You can see few pieces of furniture around you, but nothing unusual ...";
		}
		s+="You found a strange " + exitMechanism + " on the wall ...";
		for(Path p : hiddenRooms.keySet()){
			s+="\n"+"You found a room " + p + " !";
		}
		return s;
	}
	
	public void exit(){
		this.exited = true;
	}
	
	public boolean isExited(){
		return exited;
	}
	
	public String getMechanism(){
		return exitMechanism;
	}

}
