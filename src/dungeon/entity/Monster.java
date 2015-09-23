package dungeon.entity;

public class Monster {
	private int life;
	private int degats;
	private String name;
	
	public Monster(String name, int life, int degats){
		this.name = name;
		this.degats = degats;
		this.life = life;
	}

	public int getLife() {
		return life;
	}

	public void takeDamage(int damage){
		if(life - damage <= 0){
			life = 0;
		} else{
			life = life - damage;
		}
	}

	public int getDegats() {
		return degats;
	}

	public String getName() {
		return name;
	}
}
