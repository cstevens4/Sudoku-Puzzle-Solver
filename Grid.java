import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Grid extends Application {
	private TextField[][] nums = new TextField[9][9];
	private int[][]	intNums = new int[9][9];
	private int[][] result = new int[9][9];
	private String[][] strNums = new String[9][9];
	private boolean isClickedS = false;
	private boolean isClickedR = false;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	
        GridPane g = new GridPane();
        g.setPadding(new Insets(10,10,10,10));
        g.setVgap(8);
        g.setHgap(10);
        final Separator sepHor = new Separator();
        
        for(int i = 0; i < nums.length; i++) {
        	for(int j = 0; j < nums[0].length; j++) {
        		nums[i][j] = new TextField();
        		g.getChildren().add(nums[i][j]);
        		nums[i][j].setPrefSize(25, 25);
        		GridPane.setConstraints(nums[i][j], i , j);
        		
        		
        	}
        }
                
        Button solver = new Button();
        solver.setText("Solve");
        g.add(solver, 0, 0);
        
        Button reset = new Button();
        reset.setText("Reset");
        g.add(reset, 0, 1);
        
        Label error_or_solve = new Label(null);
        g.add(error_or_solve, 0, 2);
        
        VBox v = new VBox(0);
        v.getChildren().addAll(g, solver, reset, error_or_solve);
        
        solver.setOnAction(e->{
        	clickS();
        	if(isClickedS) {
        		Puzzle puzzle = new Puzzle(intNums);
        	    
        		
        		String check = null;
        		for(int i = 0; i < nums.length; i++) {
        	        	for(int j = 0; j < nums[0].length; j++) {
        	        		if (nums[i][j].getText() == null || nums[i][j].getText().trim().isEmpty()) {
        	        			nums[i][j].setText("0");
        	        		}
        	        		
        	        		intNums[i][j] = Integer.parseInt(nums[i][j].getText()) ;
        	        		
        	        	}
        	        }
   
        		
        		if (puzzle.solve()) {
        			// output with final int array
        			result = puzzle.display();
        			
        			// output onto GUI
        			for(int i = 0; i < nums.length; i++) {
        				for(int j = 0; j < nums[0].length; j++) {
        					
        					strNums[i][j] = String.valueOf(result[i][j]);
        				}
        			}
        			
        			for(int k = 0; k < nums.length; k++) {
        				for(int l = 0; l < nums[0].length; l++) {
        					nums[k][l].setText(" " + strNums[k][l]);
        				}
        			}
        			
        			error_or_solve.setText("Puzzle Solved! Hit the reset button to try anohter puzzle!");
        			error_or_solve.setTextFill(Color.GREEN);
        			
        		} else {
        			error_or_solve.setText("Invalid Puzzle! Try again.");
        			error_or_solve.setTextFill(Color.RED);
        		}
        	}
        });
        
        // reset Button Command
        reset.setOnAction(e->{
        	clickR();
        	if(isClickedR) {
        		for(TextField[] i : nums) {
        			for(TextField j : i) {
        				j.setText("");
        			}
        		}
        	}
        });
        
        Scene s = new Scene(v, 500, 500);
        
        primaryStage.setTitle("Sudoku Puzzle Solver!");
        primaryStage.setScene(s);
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
    
    public void clickS() {
    	isClickedS = true;
    }
    
    public void clickR() {
    	isClickedR = true;
    }
}
