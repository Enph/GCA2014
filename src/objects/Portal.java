package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Portal extends Obj {
	
	public Portal(){
		super("Portal",5);
	}
	
	@Override
	public void onStep(Square on, Panel panel) {
		panel.mazes.add(panel.maze);
		panel.maze.clear();
		panel.reputPlayer(4,2);
		panel.run();
		
	}
	
}
