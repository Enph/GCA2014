package spells;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Drawable;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public abstract class Spell implements Drawable{
	private String name;
	private int texture;
	private int cast;
	private double angle;
	private FloatBuffer vertexBuffer;	// buffer holding the vertices

	protected float vertices[] = {
			-0.075f, -0.075f,  0.0f,		// V1 - bottom left
			-0.075f,  0.075f,  0.0f,		// V2 - top left
			0.075f, -0.075f,  0.0f,		// V3 - bottom right
			0.075f,  0.075f,  0.0f,		// V4 - top right

	};
	public Spell(String name, int texture, int cast){
		this.name = name;
		this.texture = texture;
		angle = 0;
		this.cast = cast;
	}
	public abstract void onClick(Square cast, Panel panel);
	
	public void rotate(Spellbook book, float y){

		float tY = y*0.15f;//*sizeFactor/glConvert;
		for(int m=0;m<4;m++){
			vertices[m*3]+=tY;
		}
		angle +=y;
		if(angle==0){
			book.setSelected(this);
		}
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.clear();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	public void draw(GL10 gl){
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	}
	public int textureIndex(){
		return texture;
	}
	@Override
	public int textureSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
