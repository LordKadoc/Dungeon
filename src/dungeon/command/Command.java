package dungeon.command;

import dungeon.Dungeon;

public abstract class Command {
	
	protected Dungeon dungeon;
	
	/**
	 * Creates a command controller specialized in a certain type of command.
	 * 
	 * @param dungeon the current dungeon level.
	 */
	public Command(Dungeon dungeon){
		this.dungeon = dungeon;
	}
	
	/**
	 * Resolves the actions happening when the corresponding command is entered by the user.
	 * 
	 * @param params the parameter(s) of the command.
	 */
	public abstract void act(String params);
	
	/**
	 * 
	 * @return the description of the command when the help command is entered by the user, consisting of :
	 * 	- the use of the command, usually : command <optional parameters>
	 * 	- the description of the command, a small text explaining its purpose.
	 */
	public abstract String description();
	
	/**
	 * Returns the action of a command, the first word of the string.
	 * 
	 * @param command the text from which you want to extract the command.
	 * 
	 * @return the action contained in the command, null if there is none.
	 */
	public static String getAction(String command){
		if(command.isEmpty()){
			return null;
		}
		String[] s = command.split(" ");
		if(s.length >= 1){
			return command.split(" ")[0];
		}
		return null;
	}
	
	/**
	 * Returns the parameters of a command, so everything but the first word
	 * 
	 * @param command the text from which you want to extract the parameters
	 * 
	 * @return the parameters contained in the command, null if there are none.
	 */
	public static String getParams(String command){
		int i = command.indexOf(" ");
		if(i==-1)
			return null;
		else
			return command.substring(i+1, command.length());
	}
	
}
