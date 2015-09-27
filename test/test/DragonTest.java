package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.entity.BabyDragon;
import dungeon.entity.Dragon;
import dungeon.room.DragonRoom;

public class DragonTest {
	Dragon dragon;
	
	@Before
	public void initMonster(){
		dragon = new BabyDragon(new DragonRoom());
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
