package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.room.DragonRoom;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;
import dungeon.room.TreasureRoom;

public class RoomTest {
	
	private Room room;
	
	@Before
	public void initSimpleRoom(){
		room = new SimpleRoom();
	}

	@Test
	public void roomIsExit(){
		room = new Exit();
		assertTrue(room.isExit());
	}
	
	@Test
	public void newRoomHasNoAdjacentRooms(){
		assertEquals(0, room.getNumberOfAdjacentRooms());
	}
	
	@Test
	public void addAdjacentRoom(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.north, room2);
		assertEquals(1, room.getNumberOfAdjacentRooms());
		assertEquals(1, room.getAllWithoutGoBack().size());
		assertEquals(1, room.getNumberOfAdjacentRooms());
		assertEquals(0, room2.getAllWithoutGoBack().size());
	}
	
	@Test
	public void clearedRoomHasNoAdjacentRooms(){
		room.addRoom(PathManager.north, new SimpleRoom());
		assertEquals(1, room.getNumberOfAdjacentRooms());
		room.clearAdjacentRooms();
		assertEquals(0, room.getNumberOfAdjacentRooms());
	}
	
	@Test
	public void getAdjacentRoomFromKey(){
		Room room2 = new SimpleRoom();
		room.addRoom(new Path("keytest"), room2);
		assertEquals(room2, room.getRoom("keytest"));
	}
	
	@Test
	public void newRoomHasNoParentDirection(){
		assertEquals(null, room.getParentDirection());
	}
	
	@Test
	public void adjacentRoomHasParentDirection(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.north, room2);
		assertEquals(PathManager.south, room2.getParentDirection());
	}
	
	@Test
	public void goToNonExistingRoomReturnsSameRoom(){
		room.clearAdjacentRooms();
		assertEquals(room, room.go("north"));
	}
	
	@Test
	public void goToExistingRoomReturnsRoom(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.south, room2);
		assertEquals(room2, room.go("south"));
	}
	
	@Test
	public void roomHasCorrectToString(){
		assertEquals("a simple room", room.toString());
		room = new Exit();
		assertEquals("an exit", room.toString());
		room = new Trap();
		assertEquals("a trap", room.toString());
		room = new TreasureRoom();
		assertEquals("a treasure room", room.toString());
		room = new DragonRoom();
		assertEquals("a dragon room", room.toString());
	}
	
	@Test
	public void roomInformationIsCorrect(){
		assertEquals("You are in a simple room.", room.getDefaultDescription());
		room = new Trap();
		assertEquals("You are in a trap.", room.getDefaultDescription());
		room = new Exit();
		assertEquals("You are in an exit.", room.getDefaultDescription());
		room = new TreasureRoom();
		assertEquals("You are in a treasure room.", room.getDefaultDescription());
	}
	
	@Test
	public void testExitExtendedDescriptionCorrect(){
		room = new Exit();
		assertEquals("You reach a massive wooden door. It seems like you finally reached the end of this dungeon ...", room.getExtendedDescription());
	}
	
	@Test
	public void testTrapExtendedDescriptionCorrect(){
		room = new Trap();
		assertEquals(null, room.getExtendedDescription());
	}
	
	@Test
	public void testDragonRoomExtendedDescriptionCorrect(){
		room = new DragonRoom();
		assertEquals("You are in a small room, with scorch marks all over the walls ... It seems like you entered a dragon's nest !!", room.getExtendedDescription());
	}
	
	@Test
	public void testDragonRoomVisited(){
		room = new DragonRoom();
		assertFalse(((DragonRoom) room).isVisited());
		((DragonRoom) room).setVisited(true);
		assertTrue(((DragonRoom) room).isVisited());
	}
	
	
}
