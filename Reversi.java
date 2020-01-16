//import java.io.*;
import java.util.Scanner;

class Dot {
    enum DotColor{W, B, x}
    private DotColor color;

    // constructor
    public Dot(){
	color = Dot.DotColor.x;
    }
    public Dot(char val){
	setColor(val);
    }

    // setter
    public void setColor(char val){
	if (val=='w' || val=='W') color = Dot.DotColor.W;
	else if (val=='b' || val=='B') color = Dot.DotColor.B;
	else color = Dot.DotColor.x;
    }

    // getter
    public DotColor getColor() {
	return color;
    }
}

class Board {
    
    private Dot[][] board;
    private int xSize;
    private int ySize;
    private int offset;

    // constructor
    public Board(){
	offset = 1;
	xSize = 8;
	ySize = 8;
	board = new Dot[ySize][xSize];
	for (int i=0; i<ySize; i++){
	    for (int j=0; j<xSize; j++){
		board[i][j] = new Dot();
	    }
	}
	
	setWhite(ySize/2, xSize/2);
	setWhite(ySize/2+1, xSize/2+1);

	setBlack(ySize/2, xSize/2+1);
	setBlack(ySize/2+1, xSize/2);
    }

    //getter
    public Dot getDot(int y, int x){
	return board[y-offset][x-offset];
    }

    //setter
    public void setDot(char c, int y, int x){
	board[y-offset][x-offset].setColor(c);
    }
    
    private void setWhite(int y, int x){
	setDot('w', y, x);
    }
    private void setBlack(int y, int x){
	setDot('b', y, x);
    }

    private boolean isCharValid(String s){
	if (s.length() == 3) {
	    char a = s.charAt(0);
	    int b = s.charAt(1) - '0' ;
	    int c = s.charAt(2) - '0';
	    return ((a=='w' || a=='b' || a=='W' || a=='B') &&
		    (b<9 && b>0) &&
		    (c<9 && c>0));
	} else return false;
    }
    	
    public boolean updateBoard(String s){
	if (!isCharValid(s)) return false;
	//else
	int y=s.charAt(1) - '0';
	int x=s.charAt(2) - '0';
	setDot(s.charAt(0), y, x);
	return true;
    }

    public void printBoard(){
	System.out.println("    1 2 3 4 5 6 7 8");
	System.out.println("                   ");
	
	for(int i=0; i<8; i++){
	    System.out.print((i+1) + "   ");
	    for (int j=0; j<8; j++){
		System.out.print(board[i][j].getColor() + " ");
	    }
	    System.out.println();
	}
    }

    public void printStats(){
	
    }

    public boolean isPlaceValid(){
	return false;
    }
    
}

public class Reversi {

    public static void main (String args []){
	Board test  = new Board();
	test.printBoard();

	Scanner cin = new Scanner(System.in);
	
	System.out.println("Input x,y and color(e.g b12)");
	String inputUsr = new String();
	while (true){
	    inputUsr = cin.next();
	    boolean isUpdated = test.updateBoard(inputUsr);
	    if(isUpdated) {
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
