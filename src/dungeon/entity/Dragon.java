package dungeon.entity;

public abstract class Dragon extends Entity {
	private int degats;
	private String name;

	public Dragon(String name, int life, int degats) {		
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

	@Override
	public String getInformations() {
		// TODO Auto-generated method stub
		return "Dragon have " + life + " hp.";
	}
}
