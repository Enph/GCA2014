package agency;


public class Move extends Position {

	private Maze maze;
	
	public Move(int x, int y, Maze maze) {
		super(x, y);
		this.maze = maze;
	}

	public Move(Position position, Maze maze) {
		super(position.getX(),position.getY());
		if(position.isObstacle()){
			obstacle();
		}
		this.maze = maze;
	}

	public Move east() {
		if(getX()+1>=maze.getWidth()||maze.get(getX()+1,getY()).isObstacle()){
			return null;
		}
		return new Move(maze.get(getX()+1,getY()),maze);
	}
	public Move west() {
		if(getX()-1<0||maze.get(getX()-1,getY()).isObstacle()){
			return null;
		}
		return new Move(maze.get(getX()-1,getY()),maze);
	}
	public Move north() {
		if(getY()+1>=maze.getHeight()||maze.get(getX(),getY()+1).isObstacle()){
			return null;
		}
		return new Move(maze.get(getX(),getY()+1),maze);
	}
	public Move south() {
		if(getY()-1<0||maze.get(getX(),getY()-1).isObstacle()){
			return null;
		}
		return new Move(maze.get(getX(),getY()-1),maze);
	}
}
