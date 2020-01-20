import java.io.*;
import java.util.*;

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

    public Direction getCellDir(Point p){
	return getCell(p).getDir();
    }

    //setter

    private void addCellDir(Point p, Direction dir){
	getCell(p).addDir(dir);
    }
    private void setCellColor(Point p, char c){
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
    private void setCellEmpty(Point p){
	getCell(p).setEmpty();
    }

    private void flipCellColor(Point p){
	getCell(p).flipColor();
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
    	
    public boolean updateBoard(Point p, CellColor c){
	if (c == CellColor.EMPTY) return false;
	//	System.out.println("test");
	if (!isPosValid(p)) return false;
	//System.out.println("test1");
	if (getCellColor(p) != CellColor.POSSIBLE) return false;
	//System.out.println("test2");
	flipAll(p, c);
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

    public int whiteCount(){
	int count = 0;
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point(i,j);
	        if (getCellColor(p) == CellColor.WHITE)
		    count++;}
	}
	return count;        
    }

    public int blackCount(){
	int count = 0;
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point(i,j);
	        if (getCellColor(p) == CellColor.BLACK)
		    count++;}
	}
	return count;
    }

    public int movesCount(){
	int count = 0;
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point(i,j);
	        if (getCellColor(p) == CellColor.POSSIBLE)
		    count++;}
	}
	return count;
    }

    private Point getNeighbor(Direction dir, Point p){
	int x = p.X();
	int y = p.Y();
	//System.out.println(y+","+x+dir);
	switch (dir){
	case U:
	    --y;
	    break;
	case UR:
	    --y;
	    ++x;
	    break;
	case R:
	    ++x;
	    break;
	case DR:
	    ++y;
	    ++x;
	    break;
	case D:
	    ++y;
	    break;
	case DL:
	    ++y;
	    --x;
	    break;
	case L:
	    --x;
	    break;
	case UL:
	    --y;
	    --x;
	    break;
	}
	//System.out.println(y+","+x+dir);
	return new Point(y, x);
    }
    
    public Direction oppositeDir(Direction dir){
	switch (dir){
	case U:
	    return Direction.D;
	case UR:
	    return Direction.DL;
	case R:
	    return Direction.L;
	case DR:
	    return Direction.UL;
	case D:
	    return Direction.U;
	case DL:
	    return Direction.UR;
	case L:
	    return Direction.R;
	default: // UL
	    return Direction.DR;
	}
    }
    
    private void generateDirMove(Direction dir,
				 CellColor color,
				 Point p){
	//get valid neighbor
	Point pNext = getNeighbor(dir, p);
	//System.out.println("1."+pNext.Y()+","+pNext.X()+dir);
	if (!isPosValid(pNext)) return;
	//System.out.println("2."+p.Y()+","+p.X()+dir);
	
	//neighbor cell must be an opposite of current cell
	if (!getCell(pNext).isOppositeOf(color)){
	    return;
	}

	//System.out.println(p.Y()+","+p.X()+dir);
	// find empty
	boolean found = false;
	do {
	    pNext = getNeighbor(dir, pNext);

	    if(!isPosValid(pNext)) break;
	    if(getCellColor(pNext) == color) break;
	    if(getCell(pNext).isEmpty()) found = true;
	    
	}while (!found);

	// if found, add opposite direction to the cell
	// to be turn to the opposite color later
	if (found){
	    //System.out.println(pNext.Y()+","+pNext.X()+dir);	    
	    setCellPossible(pNext);
	    addCellDir(pNext, oppositeDir(dir));
	} else {
	    return;
	}
    }
    
    public void generateAllMove(CellColor color){
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point tmp = new Point(i,j);
	        if (getCellColor(tmp) == CellColor.POSSIBLE){
		    restoreCell(tmp);
		    System.out.println(getCell(tmp).getDirSize()+"oke");
		}
		System.out.println(getCell(tmp).getDirSize()+"oke");
	    }
	} // DELETE THIS
	
	
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point p = new Point(i,j);
		if (getCellColor(p) != color)
		    continue;
		for (Direction dir : Direction.values())
		    //p.printPos(getNeighbor(dir, p));
		   generateDirMove(dir, color, p);
	    }
	}
	System.out.println(movesCount());
    }

    private void flipDir(Point p, CellColor c, Direction dir){
	
	if (c == CellColor.WHITE) setCellWhite(p);
	if (c == CellColor.BLACK) setCellBlack(p);

	Point pNext = getNeighbor(dir, p);

	//flip all align cell
	while (getCellColor(pNext)!= c){
	    flipCellColor(pNext);
	    pNext=getNeighbor(dir, pNext);
	    if (!isPosValid(pNext)) break;
	}
    }
    private void restoreCell(Point p){
	//set to empty again
	setCellEmpty(p);

	//clear stack of direction;
	while(getCell(p).getDirSize()>0){
	    getCellDir(p);
	}
    }
    public void flipAll(Point p, CellColor c){
	// FLIP all possible direction
	while (getCell(p).getDirSize()>0){
	    flipDir(p, c, getCellDir(p));
	}

	//restore to empty cell again
	System.out.println("here");
	for (int i=offset; i<=ySize; i++){
	    for (int j=offset; j<=xSize; j++){
		Point tmp = new Point(i,j);
	        if (getCellColor(tmp) == CellColor.POSSIBLE){
		    restoreCell(tmp);
		}
	    }
	}
    }
}
