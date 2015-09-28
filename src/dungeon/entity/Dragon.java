package dungeon.entity;

public abstract class Dragon extends Entity {
	private int degats;
	private String name;

	/**
	 * Spawns a dreadful dragon and determines its stats.
	 * @param name the type of dragon, including an adjective describing it.
	 * @param life the number of hit points of the dragon.
	 * @param degats the number of damage the dragon causes when it attacks.
	 */
	public Dragon(String name, int life, int degats) {		
		super(life);
		this.name = name;
		this.degats = degats;
	}
	
	/**
	 * 
	 * @return the attack damage of the dragon.
	 */
	public int getDegats() {
		return degats;
	}
	
	/**
	 * 
	 * @return the type/name of the dragon
	 */
	public String getName() {
		return name;
	}

	@Override
	public String getInformations() {
		// TODO Auto-generated method stub
		return "Dragon has " + life + " hp.";
	}
}
