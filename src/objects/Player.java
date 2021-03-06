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
	private Heart heart;
	private int facing = 1;

	public Player(int x, int y, int index) {
		super(x,y+1);
		move(0,1);
		this.index = index;
		this.book = new Spellbook();
		this.heart = new Heart();
		this.heart.refresh();
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
    public void reset(){
    	vertices = new float[]{
    			-0.075f, -0.05f,  0.001f,		// V1 - bottom left
    			-0.075f,  0.1f,  0.004f,		// V2 - top left
    			 0.075f, -0.05f,  0.001f,		// V3 - bottom right
    			 0.075f,  0.1f,  0.004f,		// V4 - top right
                            
    	};
    	move(0,1);
    }
	protected float vertices[] = {
			-0.075f, -0.075f,  0.001f,		// V1 - bottom left
			-0.075f,  0.075f,  0.004f,		// V2 - top left
			 0.075f, -0.075f,  0.001f,		// V3 - bottom right
			 0.075f,  0.075f,  0.004f,		// V4 - top right
                        
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
		return heart.getHealth();
	}
	
	public void loseHealth(){
		heart.loseHealth();
		Log.d("loooooooose","health");
		if(getHealth()==0){
			//you die
			Log.d("Dead","Dead");
		}
	}
	
	public int getFacing(){
		return facing;
	}
	
	public void faceLeft(){
		this.setTextureIndex(61);
		facing = 4;
	}
	
	public void faceRight(){
		this.setTextureIndex(60);
		facing = 2;
	}

	public void faceUp(){
		this.setTextureIndex(63);
		facing = 3;
	}

	public void faceDown(){
		this.setTextureIndex(62);
		facing = 1;
	}
	
	public void darkness(Panel panel){
		new DarknessThread(panel).start();
	}
	public class DarknessThread extends Thread {
			int x;
			int y;
			Panel panel;
			DarknessThread(Panel pane){
				this.x = getX();
				this.y = getY();
				this.panel = pane;
			}
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!panel.getSquare(self.getX(),self.getY()).getVisible()){
					loseHealth();
					darkness(panel);
				}
			};
	}
	Player self = this;
	@Override
	public int textureSize() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Spellbook getSpellbook() {
		return book;
	}

	public void refreshHeart(){
		heart.refresh();
	}
	
	public class Heart implements Drawable{
		private int texture;

		private int health = 3;
		private int good = 2;
		
		private FloatBuffer vertexBuffer;	// buffer holding the vertices

		protected float vertices[] = {
				-0.075f+0.6f, -0.075f-0.3f,  0.006f,		// V1 - bottom left
				-0.075f+0.6f,  0.075f-0.3f,  0.006f,		// V2 - top left
				0.075f+0.6f, -0.075f-0.3f,  0.006f,		// V3 - bottom right
				0.075f+0.6f,  0.075f-0.3f,  0.006f,		// V4 - top right

		};
		public Heart(){
			texture = 0;
		}
		
		public void draw(GL10 gl){
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
		}
		@Override
		public int textureIndex(){
			if(health<=0){
				return texture+18;
			}
			return texture+6*(3-health)+good;
		}
		@Override
		public int textureSize() {
			return 3;
		}

		public int getHealth() {
			return health;
		}

		public void loseHealth() {

			Panel.context.mRenderer.removeDrawable(this);
			health--;
			refresh();
		}
		public void refresh(){
			Panel.context.mRenderer.removeDrawable(this);
			Panel.context.mRenderer.addDrawable(this);

			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
			byteBuffer.order(ByteOrder.nativeOrder());
			vertexBuffer = byteBuffer.asFloatBuffer();
			vertexBuffer.clear();
			vertexBuffer.put(vertices);
			vertexBuffer.position(0);
		}
		public void changeGoodness(int change){
			Panel.context.mRenderer.removeDrawable(this);
			good+=change;
			refresh();
		}
	}

}

