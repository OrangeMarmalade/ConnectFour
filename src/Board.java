
public class Board {
	public enum Column {
		LEFT, CLEFT, CRIGHT, RIGHT
	}
	Cell[][] grid;
	
	int[] col;
	
	public Board() {
		grid = new Cell[4][4];
		col = new int[4];

		initializeBoard();
	}
	
	public void displayBoard() {
		
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j++) {
				Cell temp = grid[i][j];
				switch (temp.getSymbol()) {
					case X: 
						System.out.print(" " + "[X]");
						break;
					case O:
						System.out.print(" " + "[O]");
						break;
					default:
						System.out.print(" " + "[ ]");
						break;
				}
			}
			System.out.println();
		}
		
	}
	
	private void initializeBoard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = new Cell();
			}
		}
	}
	
	public void refreshBoard() {
		initializeBoard();
		col = new int[4];

	}
	
	public boolean ifWon(int colPos, Cell.CellSymbol symbol) {
		// from top left to bottom right diagonally;
		
		// checks vertical;
		for (int i = 0; i < 4; i++) {
			if (grid[i][colPos].getSymbol() != symbol) {
				break;
			}
			if (i == 3) {
				return true;
			}
		}
		
		// checks horizontal;
		// remember when we placePiece, we actually increment the respective
		// column so that it is +1 above the row in the column
		// we will have to -1 to re-adjust again
		for (int i = 0; i < 4; i++) {
			if (grid[3 - (col[colPos] - 1)][i].getSymbol() != symbol) {
				break;
			}
			if (i == 3) {
				return true;
			}
		}
		
		return checkDiagonal(symbol);
	}
	
	public boolean checkDiagonal(Cell.CellSymbol symbol) {
		for (int i = 0; i < 4; i++) {
			if (grid[i][i].getSymbol() != symbol) {
				break;
			}
			if (i == 3) {
				return true;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if (grid[3 - i][i].getSymbol() != symbol) {
				break;
			}
			if (i == 3) {
				return true;
			}
		}
		return false;
	}
	
	public boolean placePiece(Column colPos, Cell.CellSymbol symbol) {
		if (checkColumnAvailable(colPos)) {
			switch(colPos) {
				case LEFT:
					grid[3 - col[0]][0].setSymbol(symbol);
					col[0] +=1;
					break;
				case CLEFT:
					grid[3 - col[1]][1].setSymbol(symbol);
					col[1] +=1;
					break;
				case CRIGHT:
					grid[3 - col[2]][2].setSymbol(symbol);
					col[2] +=1;
					break;
				case RIGHT:
					grid[3 - col[3]][3].setSymbol(symbol);
					col[3] +=1;
				default: break;
			}
			
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean checkColumnAvailable(Column colPos) {
		switch (colPos) {
			case LEFT:
				return (col[0] <= 3) ? true : false;
				
			case CLEFT:
				return (col[1] <= 3) ? true : false;
				
			case CRIGHT:
				return (col[2] <= 3) ? true : false;
				
			case RIGHT:
				return (col[3] <= 3) ? true : false;
				
			default: return false;
		}
			
	}
	
	

}
