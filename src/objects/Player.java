package objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import spells.Light;
import spells.Spell;
import spells.Spellbook;

import com.example.gca2014.Drawable;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

import agency.Position;
import android.util.Log;

public class Player extends Position implements Drawable{
	
	private int index;
	private Spellbook book;
	private int health = 3;
	private int facing = 1;

	public Player(Square position, int index) {
		super(position.getX(),position.getY());
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.clear();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		move(0,1);
		setY(getY()-1);
		setX(getX()+1);
		this.index = index;
		this.book = new Spellbook();
		Spell light = new Light();
		this.book.add(light);
		this.book.setSelected(light);
	}
	private FloatBuffer vertexBuffer;
	static public FloatBuffer textureBuffer;	// buffer holding the texture coordinates
    static public float texture[] = {
    		// Mapping coordinates for the vertices
    		0.0f, 1.0f,		// top left		(V2)
    		0.0f, 0.0f,		// bottom left	(V1)
    		1.0f, 1.0f,		// top right	(V4)
    		1.0f, 0.0f		// bottom right	(V3)
    }; 
	protected float vertices[] = {
			-0.075f, -0.075f,  0.0f,		// V1 - bottom left
			-0.075f,  0.075f,  0.0f,		// V2 - top left
			 0.075f, -0.075f,  0.0f,		// V3 - bottom right
			 0.075f,  0.075f,  0.0f,		// V4 - top right
                        
	};
	public void move(int x, int y){
		if(x!=0){
			float tX = ((float)x)*0.15f;//*sizeFactor/glConvert;
            for(int m=0;m<4;m++){
            	vertices[m*3]+=tX;
            }
       }
		if(y!=0){
			float tY = ((float)y)*0.15f;//*sizeFactor/glConvert;
			for(int m=0;m<4;m++){
				vertices[m*3+1]-=tY;
			}
		}

		setY(getY()+y);
		setX(getX()+x);
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.clear();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}

	@Override
	public void draw(GL10 gl) {
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
	    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
	}

	@Override
	public int textureIndex() {
		return index;
	}
	
	public void setTextureIndex(int index){
		this.index = index;
	}

	public void cast(Square square, Panel panel) {
		book.cast(square, panel);
	}
	
	public int getHealth(){
		return health;
	}
	
	public void loseHealth(){
		health--;
		if(health==0){
			
		}
	}
	
	public int getFacing(){
		return facing;
	}
	
	public void faceLeft(){
		this.setTextureIndex(44);
		facing = 4;
	}
	
	public void faceRight(){
		this.setTextureIndex(43);
		facing = 2;
	}

	public void faceUp(){
		this.setTextureIndex(45);
		facing = 1;
	}

	public void faceDown(){
		this.setTextureIndex(45);
		facing = 3;
	}

}
