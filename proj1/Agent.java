import java.util.List;
import java.util.ArrayList;
public class Agent {
	private Pair start;
	private Pair curLoc; //Current location.
	private Maze maze;
	private List<Pair> path;
	public Agent(Pair start, Maze maze) {
		this.start = start;
		this.maze = maze;
		path = new ArrayList<Pair>();
	}	
	public void setLoc(int x, int y) {
		start.setX(x);
		start.setY(y);
	}
	public int getX() {
		return curLoc.getX();
	}
	public int getY() {
		return curLoc.getY();
	}
	public boolean moveUP(int x, int y) {
		//need to implement
		return true;
	}
	public boolean moveDown(int x, int y) {
		//need to implement
		return true;
	}
	public boolean moveLeft(int x, int y) {
		//need to implement
		return true;
	}
	public boolean moveRight(int x, int y) {
		//need to implement
		return true;
	}
}