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
import dungeon.Game;
import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.entity.Player;
import dungeon.item.Weapon;
import dungeon.room.Exit;
import dungeon.room.Room;
import dungeon.room.SimpleRoom;
import dungeon.room.Trap;


public class DungeonTest {

	private Dungeon dungeon;
	
	@Before
	public void createDungeon(){
		dungeon = new Dungeon(new Player(1, new Weapon("Wooden Sword", 1)));
		dungeon.getCommandManager().setAvailableCommands(dungeon.getCurrentRoom().getAvailableCommands());
	}
	
	@Test
	public void testCreateGame(){
		Game game = new Game(3);
		assertTrue(game != null);
		assertTrue(game instanceof Game);
	}
	
	@Test
	public void testDungeonIsCreated(){
		assertNotNull(dungeon);
		assertTrue(dungeon instanceof Dungeon);
	}
	
	@Test
	public void testInitialRoomIsSimpleRoom(){
		assertTrue(dungeon.getCurrentRoom() instanceof SimpleRoom);
	}
	
	@Test
	public void testGameNotFinishedAtBeginning(){
		assertFalse(dungeon.gameIsFinished());
	}
	
	@Test
	public void testDisplayIsCorrect(){	
		assertEquals("You are in a simple room.\nPlayer : you have 1 hp.\nWhat do you want to do ?\n> ",dungeon.getInformations());
	}
	
	@Test
	public void testRoomIsNotSameWhenMovementIsValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go north");
		assertNotEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void testRoomExpectedIsCorrectWhenMovementIsValid(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		Room room = dungeon.getCurrentRoom().getRoom("north");
		dungeon.interpreteCommand("go north");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void testCommandIsTooSmall(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void testCommandIsNotValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("test test");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void testDirectionIsNotValid(){
		Room room = dungeon.getCurrentRoom();
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		dungeon.interpreteCommand("go test");
		assertEquals(room, dungeon.getCurrentRoom());
	}
	
	@Test
	public void testGameWon(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Exit());
		dungeon.interpreteCommand("go north");
		assertTrue(dungeon.gameIsWon());
	}
	
	@Test
	public void testGameLost(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Trap());
		dungeon.interpreteCommand("go north");
		assertTrue(dungeon.gameIsLost());
	}
	
	@Test
	public void testRoomIsReplaced(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		Room room = dungeon.getCurrentRoom().getRoom("north");
		assertTrue(room instanceof SimpleRoom);
		dungeon.getCurrentRoom().addRoom(PathManager.north, new Trap());
		assertTrue(dungeon.getCurrentRoom().getRoom("north") instanceof Trap);
		assertNotEquals(dungeon.getCurrentRoom().getRoom("north"), room);		
	}
	
	@Test
	public void testAdjacentRoomIsCleared(){
		dungeon.getCurrentRoom().addRoom(PathManager.north, new SimpleRoom());
		assertEquals(1,dungeon.getCurrentRoom().getNumberOfAdjacentRooms());
		dungeon.getCurrentRoom().clearAdjacentRooms();
		assertEquals(0, dungeon.getCurrentRoom().getNumberOfAdjacentRooms());
	}
	
	@Test
	public void testGetAllDirections(){
		List<Path> tmp = PathManager.getAllPaths();
		List<Path> direction = new ArrayList<Path>();
		
		for(int i = 0; i < 100; i++){
			direction.add(PathManager.getRandomPath(PathManager.getAllPaths()));
		}
		
		assertTrue(direction.containsAll(tmp));
	}
	
	@Test
	public void testUnknownDirectionOppositeIsBack(){
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
