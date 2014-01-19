package objects;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Wall extends Obj {

	public Wall(int textureIndex) {
		super("Wall", 7+textureIndex);
	}

	@Override
	public void onStep(Square on, Panel panel) {
		
	}
	@Override
	public int textureSize(){
		return 1;
	}

}
