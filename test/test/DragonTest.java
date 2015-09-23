package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.BabyDragon;
import dungeon.entity.Dragon;

public class DragonTest {
	Dragon dragon;
	
	@Before
	public void initMonster(){
		dragon = new BabyDragon();
	}
	
	@Test
	public void MonsterIsDragon(){
		assertTrue(dragon instanceof Dragon);
	}
	
	@Test
	public void testGetLife(){
		assertEquals(2, dragon.getLife());
	}
	
	@Test
	public void testGetDegat(){
		assertEquals(1, dragon.getDegats());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Dragon", dragon.getName());
	}

	@Test
	public void testSetLife(){
		dragon.takeDamage(1);
		assertEquals(1, dragon.getLife());
	}
}
