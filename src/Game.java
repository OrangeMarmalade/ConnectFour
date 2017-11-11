import java.util.Scanner;

public class Game {
	
	Board gameBoard;
	int turn;
	boolean isP1Turn;
	
	Scanner sc = new Scanner(System.in);
	
	int temp;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.Play();
		
	}
	
	public Game() {
		gameBoard = new Board();
		turn = 1;
		isP1Turn = true;
	}
	
	public void restartGame() {
		turn = 1;
		isP1Turn = true;
		gameBoard = new Board();
	}
	
	public void playerTurn(int pNum, Cell.CellSymbol symbol) {
		
		boolean invalidChoice = true;
		
		while (invalidChoice) {
			sc = new Scanner(System.in); 
			
			System.out.println("This is Turn " + turn);
			gameBoard.displayBoard();
			System.out.println("Player " + pNum + " Enter Column (1 - 4) to place an " + symbol + ":");
			
			while (true) {
				try {
					temp = sc.nextInt();
					
				} catch (Exception e) {
					System.out.println("Enter a valid number!");
					sc.next();
					continue;
				}
				
				if ((temp > 4) || (temp <= 0)) {
					System.out.println("Sorry invalid choice, try again!");
				} else {
					break;
				}
				
			}
			
			switch(temp) {
				case 1:
					invalidChoice = !gameBoard.placePiece(Board.Column.LEFT, symbol);
					break;
				case 2:
					invalidChoice = !gameBoard.placePiece(Board.Column.CLEFT, symbol);
					break;
				case 3:
					invalidChoice = !gameBoard.placePiece(Board.Column.CRIGHT, symbol);
					break;
				case 4: 
					invalidChoice = !gameBoard.placePiece(Board.Column.RIGHT, symbol);
					break;
				default:
					break;
			
			}

			if(invalidChoice) {
				System.out.println();
				System.out.println("Sorry column " + temp + " is full, try again!");
				System.out.println();
				
			} 
		}			
	}
	
	public void Play() {
		
		boolean finished = false;
		
		System.out.println("Welcome to Tic Tac toe!");
		
		
		while (!finished) {
			int playerNum = isP1Turn ? 1 : 2;
			Cell.CellSymbol playerSymbol = (isP1Turn) ? (Cell.CellSymbol.X) : (Cell.CellSymbol.O);
			
			playerTurn(playerNum, playerSymbol);
			
			if (gameBoard.ifWon(temp-1, playerSymbol)) {
				gameBoard.displayBoard();
				System.out.println("Player " + playerNum + " wins on turn " + turn + "!");
				System.out.println("Would you like to try again? y/n");
				if (sc.next().equals("n")) {
					finished = true;
					System.out.println("Goodbye!");
				} else {
					restartGame();
					continue;
				}
			}
			
			isP1Turn = !isP1Turn;
			turn++;
		}			
	}
}
