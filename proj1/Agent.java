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
	public List<Pair> getPath() {
		return path;
	}
	public boolean isValidLoc(int x, int y) {
		return x >= 0 && x < maze.getColLen() && y >=0 && y < maze.getRowLen();
	}
	public boolean isBarrier(int x, int y) {
		return maze.getMaze()[x][y + 1] == '%';
	}
	//If the move to go up is valid, then return the new pair, otherwise return null
	public Pair moveUP(int x, int y) {
		return isValidLoc(x, y + 1) && !isBarrier(x, y + 1)? new Pair(x, y + 1) : null; 
	}
	//If the move to go down is valid, then return the new pair, otherwise return null
	public Pair moveDown(int x, int y) {
		return isValidLoc(x, y - 1) && !isBarrier(x, y - 1)? new Pair(x, y - 1) : null; 
	}
	//If the move to go left is valid, then return the new pair, otherwise return null
	public Pair moveLeft(int x, int y) {
		return isValidLoc(x - 1, y) && !isBarrier(x - 1, y)? new Pair(x - 1, y) : null; 
	}
	//If the move to go right is valid, then return the new pair, otherwise return null
	public Pair moveRight(int x, int y) {	
		return isValidLoc(x + 1, y) && !isBarrier(x + 1, y)? new Pair(x + 1, y) : null; 
	}
}