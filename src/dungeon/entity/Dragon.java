package dungeon.entity;

import dungeon.room.DragonRoom;

public abstract class Dragon extends Entity {
	private int degats;
	private String name;
	private DragonRoom dragonRoom;

	public Dragon(String name, int life, int degats, DragonRoom dragonRoom) {		
		super(life);
		this.name = name;
		this.degats = degats;
		this.dragonRoom = dragonRoom;
	}
	
	public boolean isDead(){
		return life <= 0;
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
	
	public DragonRoom getDragonRoom(){
		return dragonRoom;
	}
}
