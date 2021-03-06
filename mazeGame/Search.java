import java.util.*;

public class Search {
	public static void DFS(Agent ag, Maze mz, Recorder rd) {
		if(ag == null || mz == null) {
			System.out.println("BFS Search fail because of invalid input arguments.");
			return;
		}
		List<Pair> sol = new ArrayList<Pair>(); //Current soluction
		boolean[][] visited = new boolean[mz.getRowLen()][mz.getColLen()];
		dfsHelper(ag.getStart(), ag, mz, visited, sol, rd);
		rd.setCost(ag.getPath().size()); 
		Maze.printMaze(mz, ag);
	}
	private static boolean dfsHelper(Pair cur, Agent ag, Maze mz, boolean[][] visited, List<Pair> sol, Recorder rd) {
		if(mz.checkGoal(cur)) {
			sol.add(cur);
			for(Pair p : sol) {
				ag.getPath().add(p);
			}
			return true;
		}
		rd.addExpand(); //Record expand Nodes
		visited[cur.getY()][cur.getX()] = true;
		Pair up = ag.moveUp(cur, visited);
		Pair down = ag.moveDown(cur, visited);
		Pair left = ag.moveLeft(cur, visited);
		Pair right = ag.moveRight(cur, visited);
		sol.add(cur);
		if(up != null && dfsHelper(up, ag, mz, visited, sol, rd)) {
			return true;
		}
		if(left != null && dfsHelper(left, ag, mz, visited, sol, rd)) {
			return true;
		}
		if(down != null && dfsHelper(down, ag, mz, visited, sol, rd)) {
			return true;
		}
		if(right != null && dfsHelper(right, ag, mz, visited, sol, rd)) {
			return true;
		}
		sol.remove(sol.size() - 1);
		return false;
	}
	public static void BFS(Agent ag, Maze mz, Recorder rd) {
		if(ag == null || mz == null) {
			System.out.println("BFS Search fail because of invalid input arguments.");
			return;
		}
		Pair last = null; //The pair reference which refer to the last step of the game to reach the goal
		Queue<Pair> que = new LinkedList<Pair>();
		boolean[][] visited = new boolean[mz.getRowLen()][mz.getColLen()];
		visited[mz.getStart().getY()][mz.getStart().getX()] = true;
		que.add(mz.getStart());
		while(mz.getGoals().size() != 0) {
			Pair cur = que.poll();
			rd.addExpand(); //Record expand Nodes
			Pair up = ag.moveUp(cur, visited);
			Pair down = ag.moveDown(cur, visited);
			Pair left = ag.moveLeft(cur, visited);
			Pair right = ag.moveRight(cur, visited);
			if(up != null) {
				last = mz.checkGoal(up)? up : last;
				visited[up.getY()][up.getX()] = true;
				up.setParent(cur);
				que.add(up);
			}
			if(down != null) {
				last = mz.checkGoal(down)? down : last;
				visited[down.getY()][down.getX()] = true;
				down.setParent(cur);
				que.add(down);
			}
			if(left != null) {
				last = mz.checkGoal(left)? left : last;
				visited[left.getY()][left.getX()] = true;
				left.setParent(cur);
				que.add(left);
			}
			if(right != null) {
				last = mz.checkGoal(right)? right : last;
				visited[right.getY()][right.getX()] = true;
				right.setParent(cur);
				que.add(right);
			}
		}
		rd.setCost(last.getGx()); //record cost
		//record the optimal path to the agent 
		Pair cur = last;
		List<Pair> path = ag.getPath();
		while(cur != null) {
			path.add(cur);
			cur = cur.getParent();
		}
		Maze.printMaze(mz, ag);
	}
	public static void Gbfs(Agent ag, Maze mz, Recorder rd) {
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
				int aVal = a.getHx();
				int bVal = b.getHx();
				if(aVal == bVal) {
					return 0;
				}
				return aVal < bVal? -1 : 1;
			}
		});
		Pair last = null; //The pair reference which refer to the last step of the game to reach the goal
		visited[mz.getStart().getY()][mz.getStart().getX()] = true;
		pq.add(mz.getStart());
		while(mz.getGoals().size() != 0) {
			Pair cur = pq.poll();
			rd.addExpand(); //Record expand Nodes
			if(mz.checkGoal(cur)) {
				last = cur;
				break;
			}
			Pair up = ag.moveUp(cur, visited);
			Pair down = ag.moveDown(cur, visited);
			Pair left = ag.moveLeft(cur, visited);
			Pair right = ag.moveRight(cur, visited);
			if(up != null) {
				visited[up.getY()][up.getX()] = true;
				up.setParent(cur);
				pq.add(up);
			}
			if(left != null) {
				visited[left.getY()][left.getX()] = true;
				left.setParent(cur);
				pq.add(left);
			}
			if(down != null) {
				visited[down.getY()][down.getX()] = true;
				down.setParent(cur);
				pq.add(down);
			}
			if(right != null) {
				visited[right.getY()][right.getX()] = true;
				right.setParent(cur);
				pq.add(right);
			}
		}
		rd.setCost(last.getGx()); //Record cost
		//record the optimal path to the agent 
		Pair cur = last;
		List<Pair> path = ag.getPath();
		while(cur != null) {
			path.add(cur);
			cur = cur.getParent();
		}
		Maze.printMaze(mz, ag);
	}
	public static void Astar(Agent ag, Maze mz, Recorder rd) {
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
				int aVal = a.getGx() + a.getHx();
				int bVal = b.getGx() + b.getHx();
				if(aVal == bVal) {
					return 0;
				}
				return aVal < bVal? -1 : 1;
			}
		});
		Pair last = null; //The pair reference which refer to the last step of the game to reach the goal
		pq.add(mz.getStart());
		while(mz.getGoals().size() != 0) {
			Pair cur = pq.poll();
			rd.addExpand(); //Record expand Nodes
			visited[cur.getY()][cur.getX()] = true;
			if(mz.checkGoal(cur)) {
				last = cur;
				break;
			}
			Pair up = ag.moveUp(cur, visited);
			Pair down = ag.moveDown(cur, visited);
			Pair left = ag.moveLeft(cur, visited);
			Pair right = ag.moveRight(cur, visited);
			if(up != null) {
				up.setParent(cur);
				pq.add(up);
			}
			if(left != null) {
				left.setParent(cur);
				pq.add(left);
			}
			if(down != null) {
				down.setParent(cur);
				pq.add(down);
			}
			if(right != null) {
				right.setParent(cur);
				pq.add(right);
			}
		}
		rd.setCost(last.getGx()); //record cost
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