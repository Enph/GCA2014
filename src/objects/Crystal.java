package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Crystal extends Obj {
	private int note;
	public Crystal(int note) {
		super("Crystal",10);
		this.note = note;
	}
	@Override
	public void onStep(Square on, Panel panel) {
		
	}

}
