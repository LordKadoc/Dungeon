package dungeon.entity;

public abstract class Entity {
	
	protected int life;
	
	public Entity(int life){
		this.life = life;
	}
	
	public abstract String getInformations();
	
	public void attack(Entity e, int damage){
		e.takeDamage(damage);
	}
	
	public void takeDamage(int damage){
		life = Math.max(0, life-damage);
	}
	
	public boolean isDead(){
		return life <= 0;
	}
	
	public int getLife(){
		return life;
	}
}
