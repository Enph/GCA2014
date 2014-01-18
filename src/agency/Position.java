package agency;

public class Position {
	private int x;
	private int y;
	private boolean obstacle;
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Position obstacle(){
		obstacle=true;
		return this;
	}
	
	public boolean isObstacle() {
		return obstacle;
	}
	
	public int getX(){
		return x; 
	}
	public int getY(){
		return y;
	}
	@Override
	public boolean equals(Object o){
		Position p = (Position)o;
		return p.x==x&&p.y==y;
	}

	public void toggleObstacle() {
		obstacle = !obstacle;
	}
}
