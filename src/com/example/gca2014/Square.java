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

import agency.Position;
import android.util.Log;

//new version
public class Square extends Position implements Drawable{
   // public static float sizeFactor=32f;
   // public static float glConvert =200f;
  //  public static float xWidth=GLRenderer.mWidth/sizeFactor;
   // public static float yHeight=GLRenderer.mHeight/sizeFactor;
    public final static String LOG_TAG = "astrobridge: ";
    public int xvel=0,yvel=1;
    public Panel pane;
    static public FloatBuffer textureBuffer;	// buffer holding the texture coordinates
    static public float texture[] = {
    		// Mapping coordinates for the vertices
    		0.0f, 1.0f,		// top left		(V2)
    		0.0f, 0.0f,		// bottom left	(V1)
    		1.0f, 1.0f,		// top right	(V4)
    		1.0f, 0.0f		// bottom right	(V3)
    }; 
    static public FloatBuffer thirdBuffers[]= new FloatBuffer[3];	// buffer holding the texture coordinates
    static public float textureThirds[][] = new float[][] {
		new float[]{
		0.0f, 1,		// top left		(V2)
		0.0f, 0.0f,		// bottom left	(V1)
		1f/3f, 1,		// top right	(V4)
		1f/3f, 0.0f		// bottom right	(V3)
		},
		new float[]{
				1f/3f, 1.0f,		// top left		(V2)
				1f/3f, 0.0f,		// bottom left	(V1)
				2f/3f, 1.0f,		// top right	(V4)
				2f/3f, 0.0f		// bottom right	(V3)
				},
				new float[]{
				2f/3f, 1.0f,		// top left		(V2)
				2f/3f, 0.0f,		// bottom left	(V1)
				1.0f, 1.0f,		// top right	(V4)
				1.0f, 0.0f		// bottom right	(V3)
				},
}; 


	private FloatBuffer vertexBuffer;	// buffer holding the vertices

	protected float vertices[] = {
			-0.075f, -0.075f,  0.0f,		// V1 - bottom left
			-0.075f,  0.075f,  0.0f,		// V2 - top left
			 0.075f, -0.075f,  0.0f,		// V3 - bottom right
			 0.075f,  0.075f,  0.0f,		// V4 - top right
                        
	};
	protected int mTexture=0;
	private boolean isVisible = true;
	public int textureIndex(){
		return mTexture;
	}
	public Square(int x, int y, int t,Panel pane) {
		super(x,y);
		this.pane = pane;
		move(x,y);
		mTexture=t;
	}

    public Square(Square s) {
    	super(s.getX(),s.getY());
    	move(s.getX(),s.getY());
    	mTexture = s.mTexture;
    }
    
 
    public void move(int x, int y){

		if(x!=0){
			setY(getY()+y);
			float tX = ((float)x)*0.15f;//*sizeFactor/glConvert;
            for(int m=0;m<4;m++){
            	vertices[m*3+1]+=tX;
            }
       }
		if(y!=0){
			mY+=x;
			float tY = ((float)y)*0.15f;//*sizeFactor/glConvert;
			for(int m=0;m<4;m++){
				vertices[m*3]+=tY;
			}
		}
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		vertexBuffer = byteBuffer.asFloatBuffer();
		vertexBuffer.clear();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	public void relocate(int x, int y){
		mX=0;
		mY=0;
		vertices = new float[]{
			-.5f, -.5f,  0.0f,		// V1 - bottom left
			-.5f,  .5f,  0.0f,		// V2 - top left
			.5f, -.5f,  0.0f,		// V3 - bottom right
			.5f,  .5f,  0.0f,		// V4 - top right
                        
		};
		move(x,y);
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
		}
	}
	public boolean isBomb(){
		return false;
	}
	public boolean isObstacle(){
		return false;
	}
	public boolean isPowerup(){
		return false;
	}
	public void setVisible(boolean b) {
		isVisible = b;
	}
}
