package dungeon.entity;

public abstract class Monster extends Entity {
	private int life;
	private int degats;
	private String name;
	
	public Monster(String name, int life, int degats){
		super(life);
		this.name = name;
		this.degats = degats;
	}

	public int getDegats() {
		return degats;
	}

	public String getName() {
		return name;
	}
}
