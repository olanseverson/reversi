import java.io.*;
import java.util.Scanner;

public class Reversi {
    static Board test;

    
    static void printStats(Board board){
	
    }
    
    static void printGame(Board board){
	printStats(board);
	board.printBoard();
	
    }
    
    public static void main (String args []){
	test  = new Board();
	
	test.printBoard();

	Scanner cin = new Scanner(System.in);

	System.out.println("Input x,y and color(e.g b12)");
	String inputUsr = new String();
	Point p = new Point(2,2);

	while (true){
	    inputUsr = cin.next();
	    boolean isUpdated = test.updateBoard(inputUsr);
	    if(isUpdated) {
		test.generateAllMove(CellColor.WHITE);
		test.printBoard();
	    } else {
		if (inputUsr.charAt(0) == 'q') break;
		else {
		    System.out.println("invalid input");
		    continue;
		}
	    }
	}
    }
}
