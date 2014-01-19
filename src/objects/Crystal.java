package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Crystal extends Obj {
	int texture;
	public Crystal(int t) {
		super("Crystal",40);
		texture = t;
	}
	@Override
	public void onStep(Square on, Panel panel) {
	}

}
