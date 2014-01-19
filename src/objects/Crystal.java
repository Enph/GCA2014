package objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Drawable;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Crystal extends Obj {
	private int note;
	public Crystal(int note) {
		super("Crystal",16);
		this.note = note;
	}
	@Override
	public void onStep(Square on, Panel panel) {
		on.setObject(null);
		panel.setOnCrystal(this);
		panel.context.mRenderer.addDrawable(new Drawable(){

			@Override
			public void draw(GL10 gl) {
				float[] vertices = new float[]{
						-0.075f*9f, -0.075f*5f,  0.006f,		// V1 - bottom left
						-0.075f*9f,  0.075f*5f,  0.006f,		// V2 - top left
						0.075f*9f, -0.075f*5f,  0.006f,		// V3 - bottom right
						0.075f*9f,  0.075f*5f,  0.006f,		// V4 - top right

				};
				ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
				byteBuffer.order(ByteOrder.nativeOrder());
				FloatBuffer vertexBuffer = byteBuffer.asFloatBuffer();
				vertexBuffer.clear();
				vertexBuffer.put(vertices);
				vertexBuffer.position(0);
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
				gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
			}

			@Override
			public int textureIndex() {
				return 0;
			}

			@Override
			public int textureSize() {
				return getNote();
			}});
	}
	public int getNote(){
		return note;
	}
}
