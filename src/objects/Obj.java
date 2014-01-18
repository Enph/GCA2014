package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Drawable;

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
	
	public int getObjectIndex(){
		return objId;
	}
}
