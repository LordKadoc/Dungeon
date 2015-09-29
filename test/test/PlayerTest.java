package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.command.AttackCommand;
import dungeon.command.HelpCommand;
import dungeon.command.PickCommand;
import dungeon.command.UseCommand;
import dungeon.entity.Dragon;
import dungeon.entity.Player;
import dungeon.item.Potion;
import dungeon.item.Weapon;

public class PlayerTest {
	Player player;

	@Before
	public void playerInit(){
		player = new Player(10, new Weapon("Wooden Sword", 1));
	}
	
	@Test
	public void testPlayerIsCreated(){
		assertTrue(player instanceof Player);
	}
	
	@Test
	public void testPlayerHasWoodenSword(){
		assertEquals("Wooden Sword", player.getWeapon().getName());
	}
	
	@Test
	public void testPlayerHasLifeAtBeginning(){
		assertTrue(player.getLife() > 0);
	}
	
	@Test
	public void testPlayerGetDamage(){
		int lifeTmp = player.getLife();
		player.takeDamage(1);
		assertTrue(player.getLife() < lifeTmp);
	}
	
	@Test
	public void testPutObjectInInventory(){
		Weapon sword = new Weapon("Wooden Sword", 1);
		player.addItem(sword);
		assertTrue(player.getItem(0) instanceof Weapon);
	}
	
	@Test
	public void testPlayerIsDead(){
		player.takeDamage(player.getLife());
		assertTrue(player.isDead());
		assertEquals(player.getLife(), 0);
	}
	
	@Test
	public void testPlayerIsNotDead(){
		assertFalse(player.isDead());
		assertTrue(player.getLife() > 0);
	}
	
	@Test
	public void testSetWeapon(){
		player.setWeapon(new Weapon("Wooden Bow", 2));
		assertEquals("Wooden Bow", player.getWeapon().getName());
	}

	@Test
	public void testGetArme(){
		assertEquals("Wooden Sword", player.getWeapon().getName());
	}
	
	@Test
	public void testGetDamage(){
		assertEquals(1, player.getWeapon().getDamage());
	}
	
	@Test
	public void testAttack(){
		Dragon dragon = new Dragon("Baby Dragon", 2, 1);
		int lifeDragon = dragon .getLife();
		player.attack(dragon, player.getWeapon().getDamage());
		assertTrue(dragon.getLife() < lifeDragon);
	}
	
	@Test
	public void testUseWeapon(){
		Weapon woodenBow = new Weapon("Wooden Bow", 2);
		woodenBow.use(player);
		assertEquals(player.getWeapon().getName(), "Wooden Bow");
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
