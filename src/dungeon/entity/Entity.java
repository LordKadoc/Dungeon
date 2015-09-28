package dungeon.entity;

public abstract class Entity {
	
	protected int life;
	
	/**
	 * Creates an entity and determines its health.
	 * 
	 * @param life the number of hit points of the entity.
	 */
	public Entity(int life){
		this.life = life;
	}
	
	/**
	 * Returns a small text displaying some informations about the entity.
	 * 
	 * @return the informations of the entity.
	 */
	public abstract String getInformations();
	
	/**
	 * Makes the entity attack another entity.
	 * 
	 * @param e the entity to attack.
	 * 
	 * @param damage the number of damage this entity inflicts to the other entity.
	 */
	public void attack(Entity e, int damage){
		e.takeDamage(damage);
	}
	
	/**
	 * Reduces the health of this entity from a certain amount.
	 * 
	 * @param damage the number of hit points this entity loses.
	 */
	public void takeDamage(int damage){
		life = Math.max(0, life-damage);
	}
	
	/**
	 * Returns whether or not this entity is dead. An entity dies when its hit points reach zero.
	 * @return whether or not the entity still has hit points.
	 */
	public boolean isDead(){
		return life <= 0;
	}
	
	/**
	 * Returns the current amount of health of the entity.
	 * 
	 * @return the number of hit points of the entity.
	 */
	public int getLife(){
		return life;
	}
}
