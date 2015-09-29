package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.command.AttackCommand;
import dungeon.command.HelpCommand;
import dungeon.command.PickCommand;
import dungeon.command.UseCommand;
import dungeon.entity.BabyDragon;
import dungeon.entity.Dragon;
import dungeon.entity.Player;
import dungeon.item.Potion;
import dungeon.item.Weapon;
import dungeon.item.WoodenBow;
import dungeon.item.WoodenSword;

public class PlayerTest {
	Player player;

	@Before
	public void playerInit(){
		player = new Player(10, new WoodenSword());
	}
	
	@Test
	public void playerIsCreated(){
		assertTrue(player instanceof Player);
	}
	
	@Test
	public void playerHasWoodenSword(){
		assertTrue(player.getWeapon() instanceof WoodenSword);
	}
	
	@Test
	public void playerHasLifeAtBeginning(){
		assertTrue(player.getLife() > 0);
	}
	
	@Test
	public void playerGetDamage(){
		int lifeTmp = player.getLife();
		player.takeDamage(1);
		assertTrue(player.getLife() < lifeTmp);
	}
	
	@Test
	public void putObjectInInventory(){
		WoodenSword sword = new WoodenSword();
		player.addItem(sword);
		assertTrue(player.getItem(0) instanceof WoodenSword);
	}
	
	@Test
	public void playerIsDead(){
		player.takeDamage(player.getLife());
		assertTrue(player.isDead());
		assertEquals(player.getLife(), 0);
	}
	
	@Test
	public void playerIsNotDead(){
		assertFalse(player.isDead());
		assertTrue(player.getLife() > 0);
	}
	
	@Test
	public void setWeapon(){
		player.setWeapon(new WoodenBow());
		assertTrue(player.getWeapon() instanceof WoodenBow);
	}

	@Test
	public void getArme(){
		assertEquals("Wooden Sword", player.getWeapon().getName());
	}
	
	@Test
	public void getDamage(){
		assertEquals(1, player.getWeapon().getDamage());
	}
	
	@Test
	public void testAttack(){
		Dragon dragon = new BabyDragon();
		int lifeDragon = dragon .getLife();
		player.attack(dragon, player.getWeapon().getDamage());
		assertTrue(dragon.getLife() < lifeDragon);
	}
	
	@Test
	public void testUseWeapon(){
		Weapon woodenBow = new WoodenBow();
		woodenBow.use(player);
		assertEquals(player.getWeapon(), woodenBow);
	}
	
	@Test
	public void testToStringItem(){
		assertEquals("Wooden Sword", player.getWeapon().toString());
	}
	
	@Test
	public void testPotioninInventoryAndremove(){
		Potion potion = new Potion();
		player.addItem(potion);
		assertTrue(player.getItem("small health potion").equals(potion));
		player.removeItem(potion);
		assertTrue(player.getItem("small health potion") == null);
	}
	
	@Test
	public void testPlayerTakePotionAndRemove(){
		Potion potion = new Potion();
		player.addItem(potion);
		potion.use(player);
		assertTrue(player.getItem("small health potion") == null);
	}
	
	@Test
	public void testAttackCommandDescription(){
		AttackCommand command = new AttackCommand(new Dungeon(player));
		assertEquals("attack : Attacks the dragon in dragon's room.", command.description());
	}
	
	@Test
	public void testHelpCommandDescription(){
		HelpCommand command = new HelpCommand(new Dungeon(player));
		assertEquals("help : Displays all the currently available commands.", command.description());
	}
	
	@Test
	public void testUseCommandDescription(){
		UseCommand command = new UseCommand(new Dungeon(player));
		assertEquals("use : Use an item or equip a weapon.", command.description());
	}
	
	@Test
	public void testPickCommandDescription(){
		PickCommand command = new PickCommand(new Dungeon(player));
		assertEquals("pick <item> : Take the item on the ground.", command.description());
	}
}
