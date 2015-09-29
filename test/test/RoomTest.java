package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dungeon.command.CommandManager;
import dungeon.direction.Path;
import dungeon.direction.PathManager;
import dungeon.entity.Player;
import dungeon.item.Item;
import dungeon.item.WoodenSword;
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
	public void testRoomIsExit(){
		room = new Exit();
		assertTrue(room.isExit());
	}
	
	@Test
	public void testNewRoomHasNoAdjacentRooms(){
		assertEquals(0, room.getNumberOfAdjacentRooms());
	}
	
	@Test
	public void testAddAdjacentRoom(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.north, room2);
		assertEquals(1, room.getNumberOfAdjacentRooms());
		assertEquals(1, room.getAllWithoutGoBack().size());
		assertEquals(1, room.getNumberOfAdjacentRooms());
		assertEquals(0, room2.getAllWithoutGoBack().size());
	}
	
	@Test
	public void testClearedRoomHasNoAdjacentRooms(){
		room.addRoom(PathManager.north, new SimpleRoom());
		assertEquals(1, room.getNumberOfAdjacentRooms());
		room.clearAdjacentRooms();
		assertEquals(0, room.getNumberOfAdjacentRooms());
	}
	
	@Test
	public void testGetAdjacentRoomFromKey(){
		Room room2 = new SimpleRoom();
		room.addRoom(new Path("keytest"), room2);
		assertEquals(room2, room.getRoom("keytest"));
	}
	
	@Test
	public void testNewRoomHasNoParentDirection(){
		assertEquals(null, room.getParentDirection());
	}
	
	@Test
	public void testAdjacentRoomHasParentDirection(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.north, room2);
		assertEquals(PathManager.south, room2.getParentDirection());
	}
	
	@Test
	public void testGoToNonExistingRoomReturnsSameRoom(){
		room.clearAdjacentRooms();
		assertEquals(room, room.go("north"));
	}
	
	@Test
	public void testGoToExistingRoomReturnsRoom(){
		Room room2 = new SimpleRoom();
		room.addRoom(PathManager.south, room2);
		assertEquals(room2, room.go("south"));
	}
	
	@Test
	public void testRoomHasCorrectToString(){
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
	public void testRoomInformationIsCorrect(){
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
	
	@Test
	public void testOnPlayerEnterDragonRoom(){
		room = new DragonRoom();
		room.onPlayerEnter(new Player(10, new WoodenSword()));
		assertTrue(((DragonRoom) room).getDragon() != null);
	}
	
	@Test
	public void testOnTurn(){
		room = new DragonRoom();
		Player player = new Player(10, new WoodenSword());
		room.onPlayerEnter(player);
		int life = player.getLife();
		room.onTurn(player);
		room.onTurn(player);
		assertTrue(life > player.getLife());
	}
	
	@Test
	public void testCommandsOnDragonRoom(){
		room = new DragonRoom();
		room.onPlayerEnter(new Player(10, new WoodenSword()));
		List<String> commandsOfDragonRoom = new ArrayList<String>();
		commandsOfDragonRoom = room.getAvailableCommands();
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.attack);
		commands.add(CommandManager.use);
		commands.add(CommandManager.inventory);
		commands.add(CommandManager.run);
		
		for(int i = 0; i < commands.size(); i++){
			assertTrue(commands.get(i).equals(commandsOfDragonRoom.get(i)));
		}
	}
	
	@Test
	public void testCommandsOnTrap(){
		room = new Trap();
		List<String> commandsOfTrap = new ArrayList<String>();
		commandsOfTrap = room.getAvailableCommands();
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.help);
		commands.add(CommandManager.inventory);
		
		for(int i = 0; i < commands.size(); i++){
			assertTrue(commands.get(i).equals(commandsOfTrap.get(i)));
		}
	}
	
	@Test
	public void testCommandsOnExit(){
		room = new Exit();
		List<String> commandsOnExit = new ArrayList<>();
		commandsOnExit = room.getAvailableCommands();
		assertTrue(commandsOnExit.size() == 0);
	}
	
	@Test
	public void testGetExtendedDescriptionOfSimpleRoom(){
		String s = "You are in an empty room. You can see few pieces of furniture around you, but nothing unusual ...";
		assertEquals(s, room.getExtendedDescription());
	}
	
	@Test
	public void testGetAvailbleCommands(){
		room = new TreasureRoom();
		List<String> commandsOfTreasureRoom = new ArrayList<>();
		commandsOfTreasureRoom = room.getAvailableCommands();
		List<String> commands = new ArrayList<String>();
		commands.add(CommandManager.help);
		commands.add(CommandManager.search);
		commands.add(CommandManager.go);
		commands.add(CommandManager.use);
		commands.add(CommandManager.inventory);
		
		for(int i = 0; i < commands.size(); i++){
			assertTrue(commands.get(i).equals(commandsOfTreasureRoom.get(i)));
		}
	}
	
	@Test
	public void testIsLootedOfTreasureRoom(){
		room = new TreasureRoom();
		((TreasureRoom) room).setLooted(true);
		assertTrue(((TreasureRoom) room).isLooted());
	}
	
	@Test
	public void testTreasureRoomHaveItem(){
		room = new TreasureRoom();
		room.onPlayerEnter(new Player(10, new WoodenSword()));
		assertTrue(((TreasureRoom) room).getItem() != null);
	}
	
	@Test
	public void testGetExtendsDescriptionOfTreasureRoom(){
		room = new TreasureRoom();
		room.onPlayerEnter(new Player(10, new WoodenSword()));
		Item i = ((TreasureRoom) room).getItem();
		String s = "You are in a treasure room. Many explorers already looted it, and it's almost empty now ... There are still a few items on the ground though ...\n - a " + i.toString() + " on the ground, just in front of you.";
		assertEquals(s , room.getExtendedDescription());
	}
	
}
