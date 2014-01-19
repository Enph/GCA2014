package objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.example.gca2014.Drawable;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public abstract class Obj implements Drawable {
	private String name;
	private int textureIndex;
	private Square square = null;
	private FloatBuffer vertexBuffer;	// buffer holding the vertices

	protected float vertices[];

	public Obj(String name, int textureIndex){
		this.name = name;
		this.textureIndex = textureIndex;
	}

	@Override
	public void draw(GL10 gl) {
		if(square!=null){
			float dx = 0.15f*(square.getX()-4);
			float dy = 0.15f*(square.getY()-3);
			vertices = new float[]{
					-0.075f+dy, -0.075f+dx,  0.001f,		// V1 - bottom left
					-0.075f+dy,  0.075f+dx,  0.001f,		// V2 - top left
					0.075f+dy, -0.075f+dx,  0.001f,		// V3 - bottom right
					0.075f+dy,  0.075f+dx,  0.001f,		// V4 - top right

			};
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
			byteBuffer.order(ByteOrder.nativeOrder());
			vertexBuffer = byteBuffer.asFloatBuffer();
			vertexBuffer.clear();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
			Panel.context.mRenderer.removeDrawable(this);
		}
	}

	@Override
	public int textureIndex() {
		return textureIndex;
	}

	public String getName(){
		return name;
	}
	public abstract void onStep(Square on, Panel panel);

	public Square getSquare(){
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

}
