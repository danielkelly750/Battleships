import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	// ArrayList to store the amount of ships that a player can have 
	ArrayList<Ship> playerShips = new ArrayList<Ship> ();

	// Initialise a grid for the player
	Grid playerGrid = new Grid();
	
	// Initialisation of placement variables so that the players choice can be stored
	static int[] placementChoice = new int[2];
	static String shipOrientation = null;
	
	static Scanner sc = new Scanner(System.in);
	
	// Function to read in a ship and store it in the ArrayList
	void add(Ship currentShip)
	{
		playerShips.add(currentShip);
	}
	
	// Function to allow the player to choose where to place their ship
	public void placeShip()
	{
		// for loop to place each ship with the limit being however many ships are in the array list
		for (int i = 0; i < playerShips.size(); i++)
		{
			boolean taken = false;
			// do while loop to initially read in the players choice of ship placement 
			do
			{
				// Inputs to ask the player which coordinate they would like to place the first unit of the ship
				System.out.println("Select an x coordinate for your " + playerShips.get(i).getName() + ":");
				placementChoice[0] = sc.nextInt();
				System.out.println("Select an y coordinate for your " + playerShips.get(i).getName() + ":");
				placementChoice[1] = sc.nextInt();
				
				taken = false;
			
				// If the coordinate is on the bottom row, the ship will be immediately placed horizontally
				if( placementChoice[0] == playerGrid.getBoardSize())
				{
					shipOrientation = "h";
				}
				// if the coordinate is on the far right column the ship will be automatically set to be vertical
				else if (placementChoice[1] == playerGrid.getBoardSize())
				{
					shipOrientation = "v";
				}
				// if the coordinate choice exceeds or is under the limit of the board size a message will occur to tell the player to enter a valid coordinate
				else if ((placementChoice[0] <= 0) || (placementChoice[0] > playerGrid.getBoardSize()) || (placementChoice[1] <= 0) || (placementChoice[1] > playerGrid.getBoardSize()))
				{
					System.out.println("Please Enter a Valid Coordinate For " + playerShips.get(i).getName());
				}
				// If the coordinate is in a valid location and not against the bottom or far right, ask the player which orientation the ship should be
				else
				{
					System.out.println("Horizontal or Vertical? (h or v)");
					shipOrientation = sc.next();
				}
				
				taken = isOccupied(placementChoice[0], placementChoice[1]);
				
			// while parameters are used to make sure that the players choice of placement are in the borders of the game board	
			} while ((placementChoice[0] <= 0) || (placementChoice[1] <= 0) || ((placementChoice[0] >= playerGrid.getBoardSize()) && (placementChoice[1] >= playerGrid.getBoardSize())) || (taken == true));
			
			if ((placementChoice[0] > 0) || (placementChoice[0] < (playerGrid.getBoardSize() + 1)) || (placementChoice[1] > 0) || (placementChoice[1] < (playerGrid.getBoardSize() + 1)))
			{
				playerGrid.setGridOccupation(placementChoice[0] - 1, placementChoice[1] - 1, shipOrientation, playerShips.get(0).getLength());
			}
			
			playerGrid.printBoard();
		}
		
		
	}
	
	public boolean isOccupied(int x, int y)
	{
		boolean occupied = false;
		
		for(int i = 0; i < playerShips.size(); i++)
		{
			// for loop to check whether all of the ship's units are in unoccupied spaces
			for(int j = 0; j < playerShips.get(i).getLength(); j++)
			{
				// if statement to see whether the ship is to be placed horizontally
				if (shipOrientation.equals("h"))
				{
					// if statement that passes the placement choice to see whether it is currently occupied
					if(playerGrid.getGridCoordinate(placementChoice[0]-1, placementChoice[1] - 1 + j) == 1)
					{
						System.out.println("There is currently a ship in the way!");
						occupied = true;
					}
				}
				// if statement to see whether the ship is to be placed vertically
				if (shipOrientation.equals("v"))
				{
					// if statement that passes the placement choice to see whether it is currently occupied
					if(playerGrid.getGridCoordinate(placementChoice[0] - 1 + j, placementChoice[1] - 1) == 1)
					{
						System.out.println("There is currently a ship in the way!");
						occupied = true;
					}
				}
				if (occupied == true)
				{
					return occupied;
				}
			}
		}
		
		return occupied;
	}
	
	public void attack (Player player)
	{		
		// Inputs to ask the player which coordinate they would like to place the first unit of the ship
		System.out.println("Select an x coordinate to attack: ");
		placementChoice[0] = sc.nextInt();
		System.out.println("Select an y coordinate to attack: ");
		placementChoice[1] = sc.nextInt();
		
		if (player.playerGrid.getGridCoordinate(placementChoice[0] - 1, placementChoice[1] - 1) == 1)
		{
			// 2 = hit 
			player.playerGrid.setGridCoordinate(placementChoice[0] - 1, placementChoice[1] - 1, 2);
		}
		else 
		{
			// 3 = miss
			player.playerGrid.setGridCoordinate(placementChoice[0] - 1, placementChoice[1] - 1, 3);
		}
		
	}
	
}
