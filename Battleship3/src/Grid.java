
public class Grid {
	private static int unoccupied = 0, occupied = 1, hit = 2, miss = 3;
	private String[] visuals = {"~", "X", "O"};
	private int[][] gridCoord;
	private static int boardSize;
	
	
	// Constructor that initialises the boards size, and sets each coordinate to unoccupied
	public Grid()
	{
		boardSize = 10;
		
		gridCoord = new int [boardSize] [boardSize];
		
		for (int row = 0; row < boardSize; row++)
		{
			for (int column = 0; column < boardSize; column++)
			{
				gridCoord[row][column] = unoccupied;
			}
		}
	}
	
	
	// Returns the size of the board for boundary purposes
	public int getBoardSize()
	{
		return boardSize;
	}
	
	public int getGridCoordinate(int x, int y)
	{
		return gridCoord[x][y];
	}
	
	public void setGridCoordinate(int x, int y, int set)
	{
		gridCoord[x][y] = set;
	}
	
	public void setGridOccupation(int x, int y, String orientation, int shipL)
	{		
		if(gridCoord[x][y] == unoccupied)
		{
			if (orientation.equals("h"))
			{
				for (int i = 0; i < shipL; i++)
				{
					gridCoord[x][y + i] = occupied;
				}
				
			}
			else if (orientation.equals("v"))
			{
				for (int i = 0; i < shipL; i++)
				{
					gridCoord[x + i][y] = occupied;
				}
			}
		}
	}
	
	public void printBoard()
	{
			
		for (int i = 0; i < boardSize; i++)
		{
			System.out.print("\t" + (i + 1));
		}
		
		System.out.println("\n");
				
		for(int row = 0; row < boardSize; row++)
		{
			System.out.print((row + 1) + "\t");
			for (int column = 0; column < boardSize; column++)
			{
				if((getGridCoordinate(row, column) == unoccupied)||(getGridCoordinate(row, column) == occupied))
				{
					System.out.print(visuals[0] + "\t");
				}
				else if(getGridCoordinate(row, column) == hit)
				{
					System.out.print(visuals[1] + "\t");
				}
				else if(getGridCoordinate(row, column) == miss)
				{
					System.out.print(visuals[2] + "\t");
				}
				//System.out.print(getGridCoordinate(row, column) + "\t");
			}
			System.out.println("\n");
		}
		for (int i = 0; i < 5; i++) {
			System.out.println();
		}
	}
	
}
