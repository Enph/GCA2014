package agency;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class Maze extends ArrayList<ArrayList<Position>> implements Comparable<Maze>{
	private static final long serialVersionUID = -4974919269738609258L;
	private Position goal;
	private static int width=30;
	private static int height=30;
	private int length = 0;

	public Maze(boolean generate) {
		super(width);
	}
	public int getCost(Path path) {
		int cost = 0;
		int x = path.getLast().x;
		int y = path.getLast().y;
		while(y<goal.y){
			++y;
			++cost;
			if(get(x,y).isObstacle()){
				++cost;
			}
		}
		while(y>goal.y){
			--y;
			++cost;
			if(get(x,y).isObstacle()){
				++cost;
			}
		}
		while(x<goal.x){
			++x;
			++cost;
			if(get(x,y).isObstacle()){
				++cost;
			}
		}
		while(x>goal.x){
			--x;
			++cost;
			if(get(x,y).isObstacle()){
				++cost;
			}
		}
		return cost;
	}
	public int getWidth() {
		return width;
	}
	public Position get(int x, int y) {
		return get(x).get(y);
	}
	public int getHeight() {
		return height;
	}
	public Position getGoal() {
		return goal;
	}
	@Override
	public int compareTo(Maze maze) {
		int cost = maze.length-length;
		return cost==0?-1:cost;
	}


}
