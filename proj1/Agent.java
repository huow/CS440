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
		return maze.getMaze()[y][x] == '%';
	}
	//If the move to go up is valid, then return the new pair, otherwise return null
	public Pair moveUp(Pair curLoc, boolean[][] visited) {
		int x = curLoc.getX();
		int y = curLoc.getY();
		return isValidLoc(x, y - 1) && !isBarrier(x, y - 1) && !visited[y - 1][x]? new Pair(x, y - 1, curLoc.getGx(), curLoc.getGoals()) : null; 
	}
	//If the move to go down is valid, then return the new pair, otherwise return null
	public Pair moveDown(Pair curLoc, boolean[][] visited) {
		int x = curLoc.getX();
		int y = curLoc.getY();
		return isValidLoc(x, y + 1) && !isBarrier(x, y + 1) && !visited[y + 1][x]? new Pair(x, y + 1, curLoc.getGx(), curLoc.getGoals()) : null; 
	}
	//If the move to go left is valid, then return the new pair, otherwise return null
	public Pair moveLeft(Pair curLoc, boolean[][] visited) {
		int x = curLoc.getX();
		int y = curLoc.getY();
		return isValidLoc(x - 1, y) && !isBarrier(x - 1, y) && !visited[y][x - 1]? new Pair(x - 1, y, curLoc.getGx(), curLoc.getGoals()) : null; 
	}
	//If the move to go right is valid, then return the new pair, otherwise return null
	public Pair moveRight(Pair curLoc, boolean[][] visited) {	
		int x = curLoc.getX();
		int y = curLoc.getY();
		return isValidLoc(x + 1, y) && !isBarrier(x + 1, y) && !visited[y][x + 1]? new Pair(x + 1, y, curLoc.getGx(), curLoc.getGoals()) : null; 
	}
}