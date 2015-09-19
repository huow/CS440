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
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>(mz.getRowLen() * mz.getColLen(), new Comparator<Pair>(){
			@Override
			public int compare(Pair a, Pair b) {
				if(a.equals(b)) {
					return 0;
				}
				return a.getGx() + a.getHx() < b.getGx() + b.getHx()? -1 : 1;
			}
		});
		visited[mz.getStart().getY()][mz.getStart().getX()] = true;
		pq.add(mz.getStart());
		while(mz.getGoals().size() != 0) {
			Pair cur = pq.poll();
			Pair up = ag.moveUp(cur, visited);
			Pair down = ag.moveDown(cur, visited);
			Pair left = ag.moveLeft(cur, visited);
			Pair right = ag.moveRight(cur, visited);
			if(up != null) {
				mz.checkGoal(up);
				visited[up.getY()][up.getX()] = true;
				pq.add(up);
			}
			if(down != null) {
				mz.checkGoal(down);
				visited[down.getY()][down.getX()] = true;
				pq.add(down);
			}
			if(left != null) {
				mz.checkGoal(left);
				visited[left.getY()][left.getX()] = true;
				pq.add(left);
			}
			if(right != null) {
				mz.checkGoal(right);
				visited[right.getY()][right.getX()] = true;
				pq.add(right);
			}
		}
		Maze.printMaze(mz, ag);
	}
}