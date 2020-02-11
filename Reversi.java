import java.io.*;
import java.util.*;

enum CellColor{WHITE, BLACK, EMPTY, POSSIBLE}
enum Direction{U, UR, R, DR, D, DL, L, UL}

public class Reversi {
    private static Board board;
    private static Player PlayerOne;
    private static Player PlayerTwo;

    private static CellColor whoIsPlaying;
    private static String inputUsr;

    private static Scanner cin;
    
    public  Reversi (){
	PlayerOne = new Player(CellColor.BLACK, "PlayerOne");
	PlayerTwo = new Player(CellColor.WHITE, "PlayerTwo");
	board = new Board();
	whoIsPlaying = CellColor.BLACK;
	inputUsr = new String("");
	cin = new Scanner(System.in);
    }
    
    static void printStats(){
	System.out.println('\n' + PlayerOne.getName() + " | "
			   + PlayerTwo.getName());
	
	System.out.println(" "+PlayerOne.getColor() + "    |    "
			   + PlayerTwo.getColor());
	
	System.out.println("     "+ PlayerOne.getColorNum() + "    |    "
			   + PlayerTwo.getColorNum());
    }
    
    static void printGame(String info){
	clearScreen();
	PlayerOne.setColorNum(board.blackCount());
	PlayerTwo.setColorNum(board.whiteCount());
	board.printBoard();
	printStats();
	System.out.println(info);
    }

    static void clearScreen() {  
	System.out.print("\033[H\033[2J");  
	System.out.flush();  
    }
    
    static void initialization(){
	Reversi ReversiGame = new Reversi();
	clearScreen();
	printGame("Press Enter to continue...");
	System.out.println("'q' to quit.");

	try{System.in.read();}
	catch(Exception e){}
    }
    
    static CellColor charToColor(char c){
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

    static void changePlayer(){
	if (whoIsPlaying == CellColor.WHITE){
	    whoIsPlaying = CellColor.BLACK;
	    return;
	} else {
	    whoIsPlaying = CellColor.WHITE;
	    return;
	}
    }

    static Player getCurrentPlayer(CellColor c){
	if(PlayerOne.getColor() == c)
	    return PlayerOne;
	else
	    return PlayerTwo;
    }
    

    static void runGame(CellColor c){
	Player player = getCurrentPlayer(c);
	board.generateAllMove(player.getColor());

	player.setMoveNum(board.movesCount());
	// if there is no possible move, changePlayer
	if (player.getMoveNum() == 0){
	    changePlayer();
	    return;
	}

	//print board and game stats
	printGame("\nPlayer`s turn: "+player.getColor());

	// get coordinate from user and flip the board
	Point point;
	boolean isUpdated = false;
	while (isUpdated == false){

	    //get valid input
	    do {
		System.out.print("Input point (e.g 12 )\n> ");
		inputUsr=cin.next();
		if((inputUsr.charAt(0) == 'q') && inputUsr.length()==1){
		    System.out.println("Program closed.");
		    System.exit(0);
		}
		printGame("\nInvalid Input");
	    } while (inputUsr.length()<2);

	    point = new Point (inputUsr.charAt(0)-'0',
			       inputUsr.charAt(1)-'0');
	    if (point.X() < 0 ||
		point.X() > 9 ||
		point.Y() < 0 ||
		point.Y() > 9){
		
		printGame("\nNon-number char!");
		continue;
	    }
	    
    	    // update board from user input (coordinate)
	    isUpdated = board.updateBoard(point, c);
	    
	    if (isUpdated == false){
		printGame("\nFill on the available spot!");
	    }
	}
	changePlayer();
    }
    
    public static void main (String args []){
	initialization();
	clearScreen();

	//run game while there is possible move
	do {
	    runGame(whoIsPlaying);
	}while (!(PlayerOne.getMoveNum()==0 &&
		  PlayerTwo.getMoveNum()==0));

	//the game ended
	if (PlayerOne.getColorNum()>PlayerTwo.getColorNum()){
	    printGame("PlayerOne WIN");
	} else if(PlayerOne.getColorNum()<PlayerTwo.getColorNum()){
	    printGame("PlayerTwo WIN");
	} else {
	    printGame("DRAW");
	}
	
    }
}
