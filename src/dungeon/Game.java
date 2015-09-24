package dungeon;

import dungeon.entity.Player;
import dungeon.entity.WoodenSword;

public class Game {
	
	private Dungeon dungeon;
	
	private Player player;
	
	private int currentLevel,maxLevel;
	
	public Game(int maxLevel){
		this.maxLevel = maxLevel;
		this.player = new Player(10, new WoodenSword());
		this.currentLevel = 1;
	}
	
	public void playGame(){
		
		System.out.println("********** Welcome in the Dragon's Cave !!! **********");
		
		while(currentLevel <= maxLevel){
			
			createDungeon();
			
			System.out.println("You enter the level " + currentLevel + " of the dungeon !");
			
			dungeon.start();
			
			currentLevel++;
			
		}
		
	}

	private void createDungeon() {
		dungeon = new Dungeon(player);
		new DungeonGenerator(5+currentLevel*2, dungeon).generateMainPath();;
	}

	public static void main(String[] args){
		new Game(5).playGame();
	}
	
}
