import java.io.*;
import java.util.*;

class Player {
    private String name;
    private CellColor color;
    private int countCell;

    public Player(CellColor c, String n){
	String name = new String();
	name = n;
	color = c ;
	countCell = 2;
    }

    //getter
    public String getName() {return name;}
    public int getCount() {return countCell;}
    public CellColor getColor() {return color;}

    //setter
    public void setName(String n) {name = n;}
    public void setColor(CellColor c) {color = c;}
    public void setCount(int count) {countCell = count;}
    
}
public class Reversi {
    
    private static Board board;
    private static Player PlayerOne;
    private static Player PlayerTwo;

    CellColor whoIsPlaying;
    
    public  Reversi (){
	PlayerOne = new Player(CellColor.BLACK, "PlayerOne");
	PlayerTwo = new Player(CellColor.WHITE, "PlayerTwo");

	board = new Board();

	whoIsPlaying = CellColor.BLACK;
	
    }
    static void printStats(){
	System.out.println(PlayerOne.getName() + " | "
			   + PlayerTwo.getName()); 
	System.out.println(PlayerOne.getColor() + "    |    "
			   + PlayerTwo.getColor());
	System.out.println("    "+ PlayerOne.getCount() + "    |    "
			   + PlayerTwo.getCount());
    }
    
    static void printGame(String info){
	board.printBoard();
	printStats();
	System.out.println(info);
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

    public static void initialization(){
	Reversi ReversiGame = new Reversi();
	printGame("INITIALIZATION");
    }
    public static void main (String args []){
	Scanner cin = new Scanner(System.in);

	initialization();
	
	System.out.print("Input x,y (e.g b12)\n> ");
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
