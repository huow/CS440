import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.*;

class Pair {
	private int x;
	private int y;
	private int gx;
	private int hx;
	private int calGx(int oldGx) {
		return oldGx + 1;
	}
	private int calManhattan(int x, int y, HashSet<Pair> goals) {
		int manhattan = 0;
		for(Pair p : goals) {
			manhattan += Math.abs(p.getX() - x) + Math.abs(p.getY() - y);
		}
		return manhattan;
	}
	public Pair(int x, int y, int gx, HashSet<Pair> goals) {
		this.x = x;
		this.y = y;
		this.gx = calGx(gx);
		this.hx = calManhattan(x, y, goals);
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
public class Maze {
	private Pair start;
	private HashSet<Pair> goals;
	private int rowLen; //the length of rows, corrspond to the range of y
	private int colLen;	//the length of columns, corrspond to the range of x
	private char[][] maze;
	//Constructor
	public Maze(String fileName) {
		start = new Pair(0, 0);
		goals = new HashSet<Pair>();
		maze = readMaze(fileName);
		rowLen = maze.length;
		colLen = maze[0].length;
	}
	//Read the maze from the input
	private char[][] readMaze(String fileName) {
		String line = null;
		List<char[]> helper = new ArrayList<char[]>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
                helper.add(line.toCharArray());
            }
		} catch(Exception e) {
			System.exit(1);
		}
		char[][] newMaze = new char[helper.size()][];
		for(int i = 0; i < helper.size(); i++) {
			newMaze[i] = helper.get(i);
		}
		for(int i = 0; i < newMaze.length; i++) {
			for(int j = 0; j < newMaze[0].length; j++) {
				if(newMaze[i][j] == 'P') {
					start = new Pair(i, j);
				} else if(newMaze[i][j] == '.') {
					goals.add(new Pair(i, j));
				}
			}
		}
		return newMaze;
	}
	public Pair getStart() {
		return start;
	}
	//Get the remaining goals, return null if no goals left.
	public Pair getGoal() {
		if(goals.size() == 0) {
			return null;
		} else {
			return goals.get(0);
		}
	}
	public char[][] getMaze() {
		return maze;
	}
	public int getRowLen() {
		return rowLen;
	}
	public int getColLen() {
		return colLen;
	}
	//******************For testing*********************
	public static void printMaze(Maze mz, Agent ag) {
		if(mz == null) {
			return;
		}
		char[][] map = mz.getMaze();
		char[][] copy = new char[map.length][map[0].length];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				copy[i][j] = map[i][j];
			}
		}
		if(ag != null) {
			for(Pair p : ag.getPath()) {
				copy[p.getX()][p.getY()] = 'A';
			}
		}
		for(int i = 0; i < copy.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				System.out.print(copy[i][j]);
			}
			System.out.println();
		}
	}
}