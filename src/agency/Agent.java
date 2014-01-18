package agency;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;



public class Agent {
	private TreeSet<Path> paths;
	private boolean finished;
	private String name;
	private List<Move> previousMoves = new ArrayList<Move>();
	private boolean failed;
	private Maze maze;
	private int trail;
	public Agent(String name, int i, int j, Maze maze) {
		this.name = name;
		this.maze = maze;
		this.trail = 0;
		paths = new TreeSet<Path>();
		finished = false;
		Path start = new Path(maze);
		Move startMove = new Move(i,j, maze);
		start.add(startMove);
		paths.add(start);
		previousMoves.add(startMove);
		this.name+=startMove.toString();
		failed = false;
	}
	public boolean possible(Maze maze, Position goal){
		while(!failed&&!finished){
			step(goal);
		}
		return !failed;
	}
	public void step(Position goal){
		//System.out.println(paths);
		try {
			++this.trail;
			Path first = paths.first();
			List<Path> clones = new ArrayList<Path>();
			Move move = first.getLast().east();
			if(move!=null&&!previousMoves.contains(move)){
				Path clone = first.clone();
				clone.add(move);
				clones.add(clone);
				previousMoves.add(move);
			}
			move = first.getLast().north();
			if(move!=null&&!previousMoves.contains(move)){
				Path clone = first.clone();
				clone.add(move);
				clones.add(clone);
				previousMoves.add(move);
			}
			move = first.getLast().west();
			if(move!=null&&!previousMoves.contains(move)){
				Path clone = first.clone();
				clone.add(move);
				clones.add(clone);
				previousMoves.add(move);
			}
			move = first.getLast().south();
			if(move!=null&&!previousMoves.contains(move)){
				Path clone = first.clone();
				clone.add(move);
				clones.add(clone);
				previousMoves.add(move);
			}
			//System.out.println("clones:"+clones);
			paths.remove(first);
			for(Path clone:clones){
				if(clone.hasReachedEnd(goal)){
					finished = true;
				}
				paths.add(clone);
			}
		} catch (NoSuchElementException e) {
			//System.out.println(name+" was unable to find a path to the goal.");
			finished = true;
			failed = true;
		}

	}
	public boolean isFinished(){
		return finished;
	}
	public String toString(){
		return name+" "+paths.first().toString()+" in "+paths.first().size()+" steps";
	}
	public boolean hasFailed() {
		return failed||paths.isEmpty();
	}
}
