import java.io.*;
import java.util.*;


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
	board.printBoard();
	printStats();
	System.out.println(info);
    }

    static void clearScreen() {  
	//	System.out.print("\033[H\033[2J");  
	//System.out.flush();  
    }
    
    static void initialization(){
	Reversi ReversiGame = new Reversi();
	clearScreen();
	printGame("Press Enter to continue...");

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

	// update all stats before printing
	if (player.getColor() == CellColor.WHITE){
	    player.setColorNum(board.whiteCount());
	} else {
	    player.setColorNum(board.blackCount());
	}

	//print board and game stats
	printGame("\nPlayer`s turn: "+player.getColor());

	// get coordinate from user and flip the board
	Point point;
	boolean isUpdated = false;
	while (isUpdated == false){
	    System.out.print("Input point (e.g 12 )\n> ");
	    inputUsr = cin.next();
	    point = new Point (inputUsr.charAt(0)-'0',
			       inputUsr.charAt(1)-'0');
	    isUpdated = board.updateBoard(point, c);
	    if (isUpdated == false){
		printGame("Fill on the available spot!");
	    }
	}
	changePlayer();
    }
    
    public static void main (String args []){
	initialization();
	clearScreen();

	do {
	    runGame(whoIsPlaying);
	}while (!(PlayerOne.getMoveNum()==0 &&
		  PlayerTwo.getMoveNum()==0));

	if (PlayerOne.getColorNum()>PlayerTwo.getColorNum()){
	    printGame("PlayerOne WIN");
	} else if(PlayerOne.getColorNum()<PlayerTwo.getColorNum()){
	    printGame("PlayerTwo WIN");
	} else {
	    printGame("DRAW");
	}
	
	// System.out.print("Input x,y (e.g b12)\n> ");
	// while (true){
	//     inputUsr = cin.next();
	//     Point p = new Point(inputUsr.charAt(1)-'0',
	// 			inputUsr.charAt(2)-'0');
	//     //System.out.println("test");
	//     CellColor c = charToColor(inputUsr.charAt(0));
	//     boolean isUpdated = board.updateBoard(p, c);
	//     if(isUpdated) {
	// 	board.generateAllMove(CellColor.WHITE);
	// 	board.printBoard();
	//     } else {
	// 	if (inputUsr.charAt(0) == 'q') break;
	// 	else {
	// 	    System.out.println("invalid input");
	// 	    continue;
	// 	}
	//     }
	// }

	
    }
}
