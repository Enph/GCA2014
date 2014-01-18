package agency;
import java.util.ArrayList;
import java.util.LinkedList;



public class Path extends LinkedList<Move> implements Comparable<Path>, Cloneable {
	private static final long serialVersionUID = 7146761845482530502L;
	private Maze maze;
	private Position goal;
	
	public Path(Maze maze, Position goal){
		super();
		this.maze = maze;
		this.goal = goal;
	}
	
	@Override
	public int compareTo(Path path) {
		if(path.containsAll(this)){
			return 0;
		}
		int cost = cost()-path.cost();
		return cost==0?-1:cost;
	}
	
	public int cost(){
		return maze.getCost(this,goal)+this.size();
	}
	
	public int numberOfObstaclesHit(){
		int hit=0;
		for(Move move:this){
			if(move.isObstacle()){
				++hit;
			}
		}
		return hit;
	}
	
	public Path clone(){
		Path path = new Path(maze, goal);
		path.addAll(this);
		return path;
	}

	public boolean hasReachedEnd(Position goal) {
		return getLast().equals(goal);
	}
}
