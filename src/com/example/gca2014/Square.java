package com.example.gca2014;
/**
 *
 * @author Geoff
 */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import objects.Obj;

import agency.Position;
import android.util.Log;

//new version
public class Square extends Position implements Drawable{
	// public static float sizeFactor=32f;
	// public static float glConvert =200f;
	//  public static float xWidth=GLRenderer.mWidth/sizeFactor;
	// public static float yHeight=GLRenderer.mHeight/sizeFactor;
	private Obj myobj = null;
	private Outline myOutline;
	public final static String LOG_TAG = "astrobridge: ";
	public int xvel=0,yvel=1;
	//static public FloatBuffer textureBuffer;	// buffer holding the texture coordinates
	static public float textures[][];
	static public float texture[];
	static public FloatBuffer textureBuffer;
	
	public static FloatBuffer[] textureBuffers; 


	private FloatBuffer vertexBuffer;	// buffer holding the vertices

	protected float vertices[] = {
			-0.075f, -0.075f,  0.0f,		// V1 - bottom left
			-0.075f,  0.075f,  0.0f,		// V2 - top left
			0.075f, -0.075f,  0.0f,		// V3 - bottom right
			0.075f,  0.075f,  0.0f,		// V4 - top right

	};
	protected int mTexture=0;
	protected int mTextureSize = 0;
	@Override
	public int textureSize(){
		return mTextureSize;
	}
	public void setTextureSize(int size){
		mTextureSize = size;
	}
	private boolean isVisible = false;

	public int textureIndex(){
		return mTexture;
	}
	public void setTextureIndex(int i){
		mTexture = i;
	}
	static {
		textures = new float[64][12];
		textureBuffers = new FloatBuffer[64];
		for(int t=0;t<64;++t){
			float i = (float)(t%8);
			float j = (float)((t-i)/8f);
			textures[t]= new float[]{
					// Mapping coordinates for the vertices
					i/8f, (1f+j)/8f,		// top left		(V2)
					i/8f, j/8f,		// bottom left	(V1)
					(1f+i)/8f, (1f+j)/8f,		// top right	(V4)
					(1f+i)/8f, j/8f		// bottom right	(V3)
			};
			
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12 * 4);
			byteBuffer.order(ByteOrder.nativeOrder());
			Square.textureBuffers[t] = byteBuffer.asFloatBuffer();
			Square.textureBuffers[t].put(textures[t]);
			Square.textureBuffers[t].position(0);
		}
		texture= new float[]{
				// Mapping coordinates for the vertices
				0f, 1f,		// top left		(V2)
				0f, 0f,		// bottom left	(V1)
				1f, 1f,		// top right	(V4)
				1f, 0f		// bottom right	(V3)
		};
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		Square.textureBuffer = byteBuffer.asFloatBuffer();
		Square.textureBuffer.put(texture);
		Square.textureBuffer.position(0);
	}
	public Square(int x, int y, int t) {
		super(4,3);
		myOutline = new Outline();
		move(x,y);
		mTexture=t;
	}

	public Square(Square s) {
		super(0,0);
		myOutline = s.myOutline;
		move(s.getX(),s.getY());
		isVisible = s.isVisible;
		mTexture = s.mTexture;
		mTextureSize = s.mTextureSize;
		if(s.isObstacle()){
			this.obstacle();
		}
		if(s.getObject()!=null){
			setObject(s.getObject());
		}
	}


	public Square(Square square, int x, int y) {
		super(4,3);
		myOutline = square.myOutline;
		move(x,y);
		this.isVisible = square.isVisible;
		mTexture = square.mTexture;
		mTextureSize = square.mTextureSize;
		if(square.isObstacle()){
			this.obstacle();
		}
		if(square.getObject()!=null){
			this.setObject(square.getObject());
		}
	}
	public void move(int x, int y){

			float tX = ((float)x)*0.15f;//*sizeFactor/glConvert;
			for(int m=0;m<4;m++){
				vertices[m*3+1]+=tX;
				myOutline.vertices[m*3+1]=vertices[m*3+1];
			}
			float tY = ((float)y)*0.15f;//*sizeFactor/glConvert;
			for(int m=0;m<4;m++){
				vertices[m*3]+=tY;
				myOutline.vertices[m*3]=vertices[m*3];
			}
		

		setX(getX()+x);
		setY(getY()+y);
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.clear();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		byteBuffer = ByteBuffer.allocateDirect(myOutline.vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		myOutline.vertexBuffer = byteBuffer.asFloatBuffer();
		myOutline.vertexBuffer.clear();
		myOutline.vertexBuffer.put(myOutline.vertices);
		myOutline.vertexBuffer.position(0);
	}

	public boolean equals(Object O){
		return ((Square)O).vertices[0]==vertices[0]&&
				((Square)O).vertices[1]==vertices[1];
	}
	/** The draw method for the square with the GL context */
	public void draw(GL10 gl) {
		if(isVisible )
		{
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
			myOutline.canDraw();
		}
	}
	public void setVisible(boolean b) {
		isVisible = b;
	}
	
	public void makeOutline(){

		if(isVisible){
			Panel.context.mRenderer.addDrawable(myOutline);
		}
		else {
			Panel.context.mRenderer.removeDrawable(myOutline);
		}
	}

	public Obj getObject(){
		return myobj;
	}

	public void setObject(Obj object){
		myobj = object;
		if(object != null){
			object.setSquare(this);
			Panel.context.mRenderer.addDrawable(getObject());
		}
	}

	public boolean getVisible(){
		return isVisible;
	}
	public void onStep(Panel panel) {
		if(myobj!=null){
			myobj.onStep(this, panel);
		}
	}
	public class Outline implements Drawable {
		public FloatBuffer vertexBuffer;
		public float vertices[] = {
				-0.075f, -0.075f,  0.0015f,		// V1 - bottom left
				-0.075f,  0.075f,  0.0015f,		// V2 - top left
				0.075f, -0.075f,  0.0015f,		// V3 - bottom right
				0.075f,  0.075f,  0.0015f,		// V4 - top right

		};

		int texture;
		private boolean canDraw;
		public Outline(){
			this.texture = (int) (45+Math.random()*3);
			this.canDraw = false;
		}
		
		@Override
		public void draw(GL10 gl) {
			if(canDraw){
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
				gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);
				canDraw = false;
			}
		}

		@Override
		public int textureIndex() {
			return texture;
		}

		@Override
		public int textureSize() {
			return 1;
		}
		
		public void canDraw(){
			canDraw  = true;
		}
		
	}
}
