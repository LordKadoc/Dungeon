package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dungeon.Dungeon;
import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.entity.Player;
import dungeon.entity.WoodenSword;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;
import dungeon.room.TreasureRoom;


public class DungeonTest {

	private Dungeon dungeon;
	
	@Before
	public void createDungeon(){
		dungeon = new Dungeon(new Player(1, new WoodenSword()));
		dungeon.getCommandManager().setAvailableCommands(dungeon.getCurrentRoom().getAvailableCommands());
	}
	
	@Test
	public void dungeonIsCreated(){
		assertNotNull(dungeon);
		assertTrue(dungeon instanceof Dungeon);
	}
	
	@Test
	public void initialRoomIsSimpleRoom(){
		assertTrue(dungeon.getCurrentRoom() instanceof SimpleRoom);
	}
	
	@Test
	public void gameNotFinishedAtBeginning(){
		assertFalse(dungeon.gameIsFinished());
	}
	
	@Test
	public void roomInformationIsCorrect(){
		Room room = dungeon.getCurrentRoom();
		assertEquals("You are in a simple room.", room.getDefaultDescription());
		room = new Trap();
		assertEquals("You are in a trap.", room.getDefaultDescription());
		room = new Exit();
		assertEquals("You are in an exit.", room.getDefaultDescription());
		room = new TreasureRoom();
		assertEquals("You are in a treasure room.", room.getDefaultDescription());
	}
	
	@Test
	public void displayIsCorrect(){	
		assertEquals("You are in a simple room.\nPlayer : you have 1 hp.\nWhat do you want to do ?\n> ",dungeon.getInformations());
	}
	
	@Test
	public void roomIsNotSameWhenMovementIsValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go north");
		assertNotEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void roomExpectedIsCorrectWhenMovementIsValid(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		Room room = dungeon.getCurrentRoom().getRoom("north");
		dungeon.interpreteCommand("go north");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void commandIsTooSmall(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void commandIsNotValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("test test");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void directionIsNotValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go test");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void gameWon(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Exit());
		dungeon.interpreteCommand("go north");
		assertTrue(dungeon.gameIsWon());
	}
	
	@Test
	public void gameLost(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Trap());
		dungeon.interpreteCommand("go north");
		assertTrue(dungeon.gameIsLost());
	}
	
	@Test
	public void RoomIsReplaced(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		Room room = dungeon.getCurrentRoom().getRoom("north");
		assertTrue(room instanceof SimpleRoom);
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Trap());
		assertTrue(dungeon.getCurrentRoom().getRoom("north") instanceof Trap);
		assertNotEquals(dungeon.getCurrentRoom().getRoom("north"), room);		
	}
	
	@Test
	public void adjacentRoomIsCleared(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		assertEquals(1,dungeon.getCurrentRoom().getNumberOfAdjacentRooms());
		dungeon.getCurrentRoom().clearAdjacentRooms();
		assertEquals(0, dungeon.getCurrentRoom().getNumberOfAdjacentRooms());
	}
	
	@Test
	public void getAllDirections(){
		List<Path> tmp = PathManager.getAllPaths();
		List<Path> direction = new ArrayList<Path>();
		
		for(int i = 0; i < 100; i++){
			direction.add(PathManager.getRandomPath(PathManager.getAllPaths()));
		}
		
		assertTrue(direction.containsAll(tmp));
	}
	
	@Test
	public void unknownDirectionOppositeIsBack(){
		assertEquals(PathManager.back,PathManager.getOppositePath(new Path("")));
		assertEquals(PathManager.back, PathManager.getOppositePath(new Path("jhehfuh")));
		assertEquals(null, PathManager.getOppositePath(null));
	}
	
	@Test
	public void oppositeDirectionsAreCorrect(){
		assertEquals(PathManager.south,PathManager.getOppositePath(PathManager.north));
		assertEquals(PathManager.north,PathManager.getOppositePath(PathManager.south));
		assertEquals(PathManager.west,PathManager.getOppositePath(PathManager.east));
		assertEquals(PathManager.east,PathManager.getOppositePath(PathManager.west));
	}
	
}
