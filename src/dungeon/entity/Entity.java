package dungeon.entity;

public abstract class Entity {
	private int life;
	
	public Entity(int life){
		this.life = life;
	}
	
	public void attack(Entity e, int damage){
		e.takeDamage(damage);
	}
	
	public void takeDamage(int damage){
		if(life - damage <= 0){
			life = 0;
		} else{
			life = life - damage;
		}
	}
	
	public boolean isDead(){
		if(life > 0)
			return false;
		else
			return true;
	}
	
	public int getLife(){
		return life;
	}
}
