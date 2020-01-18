import java.io.*;
import java.util.*;

enum CellColor{WHITE, BLACK, EMPTY, POSSIBLE}
enum Direction{U, UR, R, DR, D, DL, L, UL}

class Cell {
    //enum CellColor{WHITE, BLACK, EMPTY, POSSIBLE}
    //enum Direction{U, UR, R, DR, D, DL, L, UL}
    
    private CellColor color;
    private List<Direction> possibleMove;

    
    // constructor
    public Cell(){
	color = CellColor.EMPTY;
	possibleMove = new ArrayList<Direction>();
    }

    // setter
    public void setDir(Direction d){
	possibleMove.add(d);
    }
    public void setColor(char val){
	if (val=='w' || val=='W') color = CellColor.WHITE;
	if (val=='b' || val=='B') color = CellColor.BLACK;
	if (val=='p') color = CellColor.POSSIBLE;
	if (val=='e') color = CellColor.EMPTY;
    }

    public void setWhite(){
	setColor('w');
    }
    public void setBlack(){
	setColor('b');
    }
    public void setPossible(){
	setColor('p');
    }

    // getter
    public CellColor getColor(){
	return color;
    }

    public boolean isOppositeOf(CellColor c){
	switch (getColor()){
	case WHITE:
	    if (c == CellColor.WHITE) return true;
	    break;
	case BLACK:
	    if (c == CellColor.BLACK) return true;
	    break;
	}
	return false;
    }
    
    public char printColor() {
	switch (color){
	case WHITE:
	    return 'W';
	case BLACK:
	    return 'B';
	case EMPTY:
	    return 164;
	default :
	    return '.';
	}
    }
    
}

class Point {
    int x;
    int y;

    public Point(){
	this.x = 0;
	this.y = 0;
    }

    public Point(int yPos, int xPos){
	this.y = yPos;
	this.x = xPos;
    }

    public void setPos(int yPos, int xPos){
	this.y = yPos;
	this.x = xPos;
    }

    public int X(){
	return this.x;
    }

    public int Y(){
	return this.y;
    }
}

public class Board {
    
    private Cell[][] board;
    private int xSize;
    private int ySize;
    private int offset;

    // constructor
    public Board(){
	offset = 1;
	xSize = 8;
	ySize = 8;
	
	board = new Cell[ySize][xSize];
	for (int i=0; i<ySize; i++){
	    for (int j=0; j<xSize; j++){
		board[i][j] = new Cell();
	    }
	}
	
	setCellWhite(new Point(ySize/2, xSize/2));
	setCellWhite(new Point(ySize/2+1, xSize/2+1));

	setCellBlack(new Point(ySize/2, xSize/2+1));
	setCellBlack(new Point(ySize/2+1, xSize/2));

    }

    //getter
    private Cell getCell(Point p){
	return board[p.Y()-offset][p.X()-offset];
    }

    public CellColor getCellColor(Point p){
	return getCell(p).getColor();
    }

    //setter
    private void setCellColor(char c, Point p){
	getCell(p).setColor(c);
    }

    private boolean isPosValid(Point p){
	return ((p.Y()>=offset && p.Y()<=ySize) &&
		(p.X()>=offset && p.X()<=xSize));
    }

    private boolean isCellPossible(Point p){
	return (getCellColor(p) == CellColor.POSSIBLE);
    }
    
    private void setCellWhite(Point p){
	getCell(p).setWhite();
    }
    private void setCellBlack(Point p){
	getCell(p).setBlack();
    }
    private void setCellPossible(Point p){
	getCell(p).setPossible();
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
	Point p = new Point(y,x);
	setCellColor(s.charAt(0), p);
	return true;
    }

    public void printBoard(){
	System.out.println("    1 2 3 4 5 6 7 8");
	System.out.println("                   ");
	
	for(int i=offset; i<=ySize; i++){
	    System.out.print((i) + "   ");
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point(i,j);
		System.out.print(getCell(p).printColor() + " ");
	    }
	    System.out.println();
	}
    }

    private boolean isPlaceValid(){
	return false;
    }

    public int whiteCount(){
	return 0;
    }

    public int blackCount(){
	return 0;
    }

    public int movesCount(){
	return 0;
    }

    private Point getNeighbor(Direction dir, Point p){
	int x = p.X();
	int y = p.Y();
	switch (dir){
	case U:
	    --y;
	case UR:
	    --y;
	    ++x;
	case R:
	    ++x;
	case DR:
	    ++y;
	    ++x;
	case D:
	    ++y;
	case DL:
	    ++y;
	    --x;
	case L:
	    --x;
	case UL:
	    --y;
	    --x;
	}
	
	return new Point(y, x);
    }
    
    
    private void generateDirMove(Direction dir,
				 CellColor color,
				 Point p){
	//get valid neighbor
	Point pNext = getNeighbor(dir, p);
	
	if (!isPosValid(pNext)) return;

	//neighbor cell must be an opposite of current cell
	if (!getCell(pNext).isOppositeOf(color)){
	    return;
	}

	// find cell that has same color
	boolean found = false;
	do {
	    pNext = getNeighbor(dir, pNext);

	    if(!isPosValid(pNext)) break;
	    
	    if(getCellColor(pNext) == color) found = true;
	    
	}while (!found);

	// if found, add opposite direction to the cell
	// to be turn to the opposite color later
	if (found){
	    
	} else {
	    return;
	}
    }
    
    public void generateAllMove(CellColor color){
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point (i,j);
		if (getCellColor(p) != color)
		    continue;
		for (Direction dir : Direction.values())
		    generateDirMove(dir, color, p);
	    }
	}
    }
}
