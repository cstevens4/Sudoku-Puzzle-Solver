public class Puzzle {

	private int[][] grid;
	private int[][] finalSol;
	
	public Puzzle(int[][] g) {
		
		grid = g;
	}
	
	public boolean solve(){
		int[][] solution = new int[9][9];
		solution = grid;
		for (int row = 0; row < 9; row++) {
			 
			for (int col = 0; col < 9; col++) {
	        	 
	          // search for empty space
				if (solution[row][col] == 0) {
	        	  
	            
		        	for (int number = 1; number <= 9; number++) {
		              
		            	if (verify(row, col, number)) {
		            	  
		            		solution[row][col] = number;
	
		            		if (solve()) { // we start backtracking recursively
		                 
		            			return true;
		            		} else {
		                	// if it doesnt work we go back
		            			solution[row][col] = 0;
		            		}
		            	}
		            }

	            return false; // we return false
	           }
	        }
	     }
		 	finalSol = solution;
		 	this.display();
	         return true; // sudoku solved
	        
	}
	
	// we check if a possible number is already in a row
		private boolean isInRow(int row, int number) {
			for (int i = 0; i < 9; i++)
				if (grid[row][i] == number)
					return true;
			
			return false;
		}
		
		// we check if a possible number is already in a column
		private boolean isInCol(int col, int number) {
			for (int i = 0; i < 9; i++)
				if (grid[i][col] == number)
					return true;
			
			return false;
		}
		
		// we check if a possible number is in its 3x3 box
		private boolean isInBox(int row, int col, int number) {
			int r = row - row % 3;
			int c = col - col % 3;
			
			for (int i = r; i < r + 3; i++)
				for (int j = c; j < c + 3; j++)
					if (grid[i][j] == number)
						return true;
			
			return false;
		}
		
	private boolean verify(int row, int col, int number) {
	
		return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
	
	}
	
	
	//returns final array to other end
	public int[][] display() {
		return finalSol;
	}
}
