import java.util.List;
import java.util.ArrayList;
import java.io.*;

class Pair {
	private int x;
	private int y;
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
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
	private List<Pair> goals;
	private int length;
	private int width;
	private int[][] maze;
	//Constructor
	public Maze(String fileName) {
		start = new Pair(0, 0);
		goals = new ArrayList<Pair>();
		maze = readMaze(fileName);
	}
	//Read the maze from the input
	private static int[][] readMaze(String fileName) {
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
		} catch(Exception e) {
			System.exit(1);
		}
		return null;
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
	public int[][] getMaze() {
		return maze;
	}
}