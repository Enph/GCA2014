package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Drawable;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public abstract class Obj implements Drawable {
	private String name;
	private int objId;
	private int textureIndex;

	public Obj(String name, int objId, int textureIndex){
		this.name = name;
		this.objId = objId;
		this.textureIndex = textureIndex;
	}

	@Override
	public int textureIndex() {
		return textureIndex;
	}
	
	public String getName(){
		return name;
	}
	
	public int getObjectIndex(){
		return objId;
	}
	
	public abstract void onStep(Square on, Panel panel);
		
}
