public class Game {
	public static boolean init(int hxType, int costMode) {
		if(hxType >=1 && hxType <=6 && costMode >= 1 && costMode <= 3) {
			Hx.setType(hxType);
			CostMode.setType(costMode);
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		//Astar running on small maze.
		init(Hx.Manhattan, CostMode.Two);
		Maze smallMaze = new Maze("mazes/openMaze.txt");
		Agent astarAg = new Agent(smallMaze);
		Recorder rd = new Recorder();
		Search.Astar(astarAg, smallMaze, rd);
		System.out.println(rd.getExpandNodes());
	}
}