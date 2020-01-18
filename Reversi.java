import java.io.*;
import java.util.*;

public class Reversi {
    static Board board;
    
    static void printStats(Board board){
	
    }
    
    static void printGame(Board board){
	printStats(board);
	board.printBoard();
	//printMessage();
    }

    public static CellColor charToColor(char c){
	switch (c){
	case 'w':
	case 'W':
	    return CellColor.WHITE;
	case 'b':
	case 'B':
	    return CellColor.BLACK;
	default:
	    break;
	}
	return CellColor.EMPTY;
    }
    public static void main (String args []){
	Scanner cin = new Scanner(System.in);

	board = new Board();

	board.generateAllMove(CellColor.WHITE);
	board.printBoard();

	System.out.println("Input x,y and color(e.g b12)");
	String inputUsr = new String();

	while (true){
	    inputUsr = cin.next();
	    Point p = new Point(inputUsr.charAt(1)-'0',
				inputUsr.charAt(2)-'0');
	    //System.out.println("test");
	    CellColor c = charToColor(inputUsr.charAt(0));
	    boolean isUpdated = board.updateBoard(p, c);
	    if(isUpdated) {
		board.generateAllMove(CellColor.WHITE);
		board.printBoard();
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
