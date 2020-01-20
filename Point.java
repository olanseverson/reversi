import java.io.*;
import java.util.*;

public class Point {
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

    public void printPos(Point p){
	System.out.println("Pos->"+ y+ "," +x);
    }
}
