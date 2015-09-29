package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.DungeonGenerator;
import dungeon.direction.PathManager;
import dungeon.entity.Player;
import dungeon.item.WoodenSword;
import dungeon.room.SimpleRoom;

public class DungeonGeneratorTest {

	DungeonGenerator dungeonGenerator;
	
	int nomberOfRoomInGoodPath = 10;
	
	@Before
	public void createDungeonGenerator(){
		dungeonGenerator = new DungeonGenerator(nomberOfRoomInGoodPath, new Dungeon(new Player(10, new WoodenSword())));
		dungeonGenerator.generateMainPath();
	}
	
	@Test
	public void testDungeonGeneratorIsCreated(){
		assertNotNull(dungeonGenerator);
		assertTrue(dungeonGenerator instanceof DungeonGenerator);
	}
	
	@Test
	public void testDungeonHasRigthNumberOfRoom(){
		assertEquals(nomberOfRoomInGoodPath, (dungeonGenerator.getPath().size()-2));
	}
	
	@Test
	public void testDungeonHasPathToExit(){
		assertTrue(dungeonGenerator.hasPath());
	}
	
	@Test
	public void testEmptyDungeonHasNoPathToExit(){
		Dungeon dungeon = new Dungeon(new Player(10, new WoodenSword()));
		dungeonGenerator = new DungeonGenerator(10, dungeon);
		dungeonGenerator.generateMainPath();
		dungeon.getCurrentRoom().clearAdjacentRooms();
		assertFalse(dungeonGenerator.hasPath());
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		assertFalse(dungeonGenerator.hasPath());
	}
}
