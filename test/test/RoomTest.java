package test;

import static org.junit.Assert.assertEquals;
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
	public void extendedDescriptionCorrect(){
		room = new DragonRoom();
		assertEquals("You are in a small room, with scorch marks all over the walls ... It seems like you entered a dragon's nest !!", room.getExtendedDescription());
	}

}
