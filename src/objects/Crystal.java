package objects;

import javax.microedition.khronos.opengles.GL10;

public class Crystal extends Obj {
	int texture;
	public Crystal(int t) {
		super("Note",0,40);
		texture = t;
	}
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}

}
