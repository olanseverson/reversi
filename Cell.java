import java.io.*;
import java.util.*;

public class Cell {
    private CellColor color;
    private Stack<Direction> possibleMove;

    
    // constructor
    public Cell(){
	color = CellColor.EMPTY;
	possibleMove = new Stack<Direction>();
    }

    // setter
    public void addDir(Direction d){
	possibleMove.push(d);
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
    public void setEmpty(){
	setColor('e');
    }

    // getter
    public Direction getDir(){
	return possibleMove.pop();
    }
    public int getDirSize(){
	return possibleMove.size();
    }
    public CellColor getColor(){
	return color;
    }

    public boolean isEmpty(){
	return (getColor() == CellColor.EMPTY ||
		getColor() == CellColor.POSSIBLE);
    }
    
    public boolean isOppositeOf(CellColor c){
	switch (c){
	case WHITE:
	    if (getColor() == CellColor.BLACK) return true;
	    break;
	case BLACK:
	    if (getColor() == CellColor.WHITE) return true;
	    break;
	}
	return false;
    }
    
    public char printColor() {
	switch (getColor()){
	case WHITE:
	    return 'W';
	case BLACK:
	    return 'B';
	case EMPTY:
	    return '-';
	default :
	    return '+';
	}
    }

    public CellColor oppositeColor(){
	switch (getColor()){
	case WHITE:
	    return CellColor.BLACK;
	case BLACK:
	    return CellColor.WHITE;
	}
	return CellColor.EMPTY;
    }

    public void flipColor(){
	switch (getColor()){
	case WHITE:
	    setBlack();
	    break;
	case BLACK:
	    setWhite();
	    break;
	}
	return;
    }
}
