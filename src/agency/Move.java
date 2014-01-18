package agency;


public class Move extends Position {

	private Maze maze;
	
	public Move(int x, int y, Maze maze) {
		super(x, y);
		this.maze = maze;
	}

	public Move(Position position, Maze maze) {
		super(position.x,position.y);
		if(position.isObstacle()){
			obstacle();
		}
		this.maze = maze;
	}

	public Move east() {
		if(x+1>=maze.getWidth()||maze.get(x+1,y).isObstacle()){
			return null;
		}
		return new Move(maze.get(x+1,y),maze);
	}
	public Move west() {
		if(x-1<0||maze.get(x-1,y).isObstacle()){
			return null;
		}
		return new Move(maze.get(x-1,y),maze);
	}
	public Move north() {
		if(y+1>=maze.getHeight()||maze.get(x,y+1).isObstacle()){
			return null;
		}
		return new Move(maze.get(x,y+1),maze);
	}
	public Move south() {
		if(y-1<0||maze.get(x,y-1).isObstacle()){
			return null;
		}
		return new Move(maze.get(x,y-1),maze);
	}
	
	public String toString(){  
		return "("+(x)+","+(y)+")";
	}
}
