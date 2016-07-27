
public class BattleshipsGame {
	
	public static void main(String[] args) {
		
		// Initialisation of Players
		Player playerOne = new Player();
		Player playerTwo = new Player();
		
		// Initialisation of Ships
		PatrolBoat boatOne = new PatrolBoat();
		
		// Add Ships to players' array list
		playerOne.add(boatOne);
		
		playerTwo.add(boatOne);
	
		
		// print player board
		// Call function to place the ships of the players
		playerOne.playerGrid.printBoard();
		playerTwo.playerGrid.printBoard();
		
		playerOne.placeShip();
		playerOne.playerGrid.printBoard();
		playerTwo.playerGrid.printBoard();
		
		playerTwo.placeShip();
		playerOne.playerGrid.printBoard();
		playerTwo.playerGrid.printBoard();
		
		// Testing for the attack function implementation
		playerOne.attack(playerTwo);
		playerTwo.playerGrid.printBoard();
		
		playerOne.attack(playerTwo);
		playerTwo.playerGrid.printBoard();
		
	}

}
