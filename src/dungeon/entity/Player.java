package dungeon.entity;

import java.util.HashMap;
import java.util.Map;

public class Player {
	
	private int life;
	private Weapon weapon;
	private Map<String, Object> inventory;
	
	public Player(int life, Weapon weapon){
		this.life = life;
		this.weapon = weapon;
		this.inventory = new HashMap<>();
	}
	
	public void attack(Monster monster){
		monster.takeDamage(this.getWeapon().getDamage());
	}
	
	public void takeDamage(int damage){
		if(life - damage <= 0){
			life = 0;
		} else{
			life = life - damage;
		}
	}
	
	public void setInventory(String s, Object o){
		inventory.put(s, o);
	}
	
	public Object getInInventory(String s){
		return inventory.get(s);
	}
	
	public int getLife(){
		return life;
	}
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
	public boolean isDead(){
		if(life > 0)
			return false;
		else
			return true;
	}
	
}
