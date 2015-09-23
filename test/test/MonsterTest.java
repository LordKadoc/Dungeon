package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.Dragon;
import dungeon.entity.Monster;

public class MonsterTest {
	Monster dragon;
	
	@Before
	public void initMonster(){
		dragon = new Dragon();
	}
	
	@Test
	public void MonsterIsDragon(){
		assertTrue(dragon instanceof Dragon);
	}
	
	@Test
	public void testGetLife(){
		assertEquals(10, dragon.getLife());
	}
	
	@Test
	public void testGetDegat(){
		assertEquals(2, dragon.getDegats());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Dragon", dragon.getName());
	}

	@Test
	public void testSetLife(){
		dragon.takeDamage(1);
		assertEquals(9, dragon.getLife());
	}
}
