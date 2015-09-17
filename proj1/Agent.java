import java.util.ArrayList;
public class Agent {
	private Agent robot;
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
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return curLoc.getX();
	}
	public int getY() {
		return curLoc.getY();
	}
	public boolean moveTo(int x, int y) {
		if(maze == null) {
			return false;
		}
		if(x )
	}
	public static Agent getRob() {
		if(robot == null) {
			robot = new Agent();
		}
		return robot;
	}
}