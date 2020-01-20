import java.io.*;
import java.util.*;

public class Player {
    private String name;
    private CellColor color;
    private int moveNum;
    private int colorNum;

    public Player(CellColor c, String n){
	name = n;
	color = c ;
	colorNum = 2;
	moveNum = 0;
    }

    //getter
    public String getName() {return name;}
    public int getColorNum() {return colorNum;}
    public CellColor getColor() {return color;}
    public int getMoveNum() {return moveNum;}

    //setter
    public void setName(String n) {name = n;}
    public void setColor(CellColor c) {color = c;}
    public void setColorNum(int count) {colorNum = count;}
    public void setMoveNum(int count) {moveNum = count;}
}
