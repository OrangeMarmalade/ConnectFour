


public class Cell {
	public enum CellSymbol {
		X, O, SPACE
	}
	CellSymbol symbol;
	
	public Cell() {
		symbol = CellSymbol.SPACE;
	}
	
	public void setSymbol(CellSymbol input) {
		symbol = input;
	}
	
	public CellSymbol getSymbol() {
		return symbol;
	}
	

}
