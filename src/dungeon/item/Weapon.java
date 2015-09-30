package dungeon.item;

import dungeon.entity.Player;

public class Weapon extends Item{
	
	protected int damage;
	
	/**
	 * Forges a new weapon, and assigns it a name and an amount of damage.
	 * 
	 * @param name the name/type of the weapon.
	 * 
	 * @param damage the amount of damage the weapons deals.
	 */
	public Weapon(String name, int damage){
		super(name);
		this.damage = damage;
	}
	
	/**
	 * 
	 * @return the damage of the weapon, how many hit points it makes an enemy lose.
	 */
	public int getDamage(){
		return damage;
	}

	@Override
	public void use(Player player) {
		System.out.println("You equip a " + this + " (" + damage + " damage)");
		Item iNow = player.getWeapon();
		player.addItem(iNow);
		player.setWeapon(this);		
		
	}

}
