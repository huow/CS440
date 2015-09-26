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
		Pair last = null; //The pair reference which refer to the last step of the game to reach the goal
		visited[mz.getStart().getY()][mz.getStart().getX()] = true;
		pq.add(mz.getStart());
		while(mz.getGoals().size() != 0) {
			Pair cur = pq.poll();
			Pair up = ag.moveUp(cur, visited);
			Pair down = ag.moveDown(cur, visited);
			Pair left = ag.moveLeft(cur, visited);
			Pair right = ag.moveRight(cur, visited);
			if(up != null) {
				last = mz.checkGoal(up)? up : last;
				visited[up.getY()][up.getX()] = true;
				up.setParent(cur);
				pq.add(up);
			}
			if(down != null) {
				last = mz.checkGoal(down)? down : last;
				visited[down.getY()][down.getX()] = true;
				down.setParent(cur);
				pq.add(down);
			}
			if(left != null) {
				last = mz.checkGoal(left)? left : last;
				visited[left.getY()][left.getX()] = true;
				left.setParent(cur);
				pq.add(left);
			}
			if(right != null) {
				last = mz.checkGoal(right)? right : last;
				visited[right.getY()][right.getX()] = true;
				right.setParent(cur);
				pq.add(right);
			}
		}
		//record the optimal path to the agent 
		Pair cur = last;
		List<Pair> path = ag.getPath();
		while(cur != null) {
			path.add(cur);
			cur = cur.getParent();
		}
		Maze.printMaze(mz, ag);
	}
}