package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Player;
import dungeon.entity.WoodenBow;
import dungeon.entity.WoodenSword;

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
		player.setInventory("Arme", new WoodenSword());
		assertTrue(player.getInInventory("Arme") instanceof WoodenSword);
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
}
