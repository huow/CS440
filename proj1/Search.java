import java.util.*;

public class Search {
	public static void DFS(Agent ag, Maze mz) {

	}
	public static void BFS(Agent ag, Maze mz) {

	}
	public static void GBFS(Agent ag, Maze mz) {

	}
	public static void AStar(Agent ag, Maze mz) {
		//Sanity check..
		if(ag == null || mz == null) {
			System.out.println("Astar Search fail because of invalid input arguments.");
			return;
		}
		char[][] board = mz.getMaze();
		boolean[][] visited = new boolean[mz.getRowLen()][mz.getColLen()];
		PriorityQueue
		Maze.printMaze(mz, ag);
	}
}