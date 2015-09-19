public class Game {
	public static void main(String[] args) {
		//Astar running on small maze.
		Maze smallMaze = new Maze("mazes/smallMaze.txt");
		Agent astarAg = new Agent(smallMaze.getStart(), smallMaze);
		Search.AStar(astarAg, smallMaze);
	}
}