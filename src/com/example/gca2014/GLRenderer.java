package com.example.gca2014;
 

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.os.Build;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GLRenderer implements Renderer {
	private MainActivity 	context;
	public static float mWidth=400;
	public static float mHeight=240;
	public static float aspectRatio=2f/3f;

    private ArrayList<DrawArray> mDraws=new ArrayList<DrawArray>();
    private ArrayList<AnimationArray> Animations=new ArrayList<AnimationArray>();
	public GLRenderer(MainActivity context) {
		this.context = context;
	}
	public void onDrawFrame(GL10 gl) {
		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// Drawing
		gl.glTranslatef(0.0f, 0.0f, -20.0f);		// move 5 units INTO the screen
		synchronized(context){
		display(gl);
		}
	}
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		previousGL = gl;
		mWidth=((float)context.glSurfaceView.getMeasuredWidthAndState());
		mHeight=((float)context.glSurfaceView.getMeasuredHeightAndState());
		//aspectRatio = mWidth/mHeight;
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, (int)mWidth, (int)mHeight); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
		
        
		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12 * 4);		
		byteBuffer.order(ByteOrder.nativeOrder());
		Square.textureBuffer = byteBuffer.asFloatBuffer();
		Square.textureBuffer.put(Square.texture);
		Square.textureBuffer.position(0);
		
		byteBuffer = ByteBuffer.allocateDirect(12 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		Square.thirdBuffers[0] = byteBuffer.asFloatBuffer();
		Square.thirdBuffers[0].put(Square.textureThirds[0]);
		Square.thirdBuffers[0].position(0);
		byteBuffer = ByteBuffer.allocateDirect(12 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		Square.thirdBuffers[1] = byteBuffer.asFloatBuffer();
		Square.thirdBuffers[1].put(Square.textureThirds[1]);
		Square.thirdBuffers[1].position(0);
		byteBuffer = ByteBuffer.allocateDirect(12 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		Square.thirdBuffers[2] = byteBuffer.asFloatBuffer();
		Square.thirdBuffers[2].put(Square.textureThirds[2]);
		Square.thirdBuffers[2].position(0);
		// Load the texture for the square
		
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do
		gl.glEnable(GL10.GL_BLEND);

		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		previousGL = gl;
		
		context.glSurfaceView.setup();
		while(!context.glSurfaceView.ready){
			
		}
	}
	public static int[] textures = new int[1];
    public static int textureSize=0;
    public void addDrawable(Drawable d){
    	//Log.d("ratjug add", Integer.toString(d.textureIndex()));
    	mDraws.get(d.textureIndex()).add(d);
    }
    public void removeDrawable(Drawable d){
    	//Log.d("ratjug remove", Integer.toString(d.textureIndex()));
    	mDraws.get(d.textureIndex()).remove(d);
    }
    public void addAnimatable(Animatable d){
    	//Log.d("ratjug add", Integer.toString(d.textureIndex()));
    	Animations.get(d.textureIndex()).add(d);
    }
    public void removeAnimatable(Animatable d){
    	//Log.d("ratjug remove", Integer.toString(d.textureIndex()));
    	Animations.get(d.textureIndex()).remove(d);
    }
    private GL10 previousGL;
    public void blitGLTexture(int D, Context context) {
    	// loading texture
    	//Log.d("ratjug",Integer.toString(D));
    	
    	int catcher[] = new int[3];
    		Bitmap bitmap = 
      	    	  BitmapFactory.decodeResource(context.getResources(), D);
    	// generate one texture pointer
    	previousGL.glGenTextures(1, textures, 0);
    	// ...and bind it to our array
    	previousGL.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

    	// create nearest filtered texture
    	previousGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_SMOOTH);
    	previousGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_SMOOTH);

    	// Use Android GLUtils to specify a two-dimensional texture image from our bitmap
    	GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    	ByteBuffer imageBuffer = ByteBuffer.allocateDirect(bitmap.getHeight() * bitmap.getWidth() * 4);
    	imageBuffer.order(ByteOrder.nativeOrder());
    	byte buffer[] = new byte[4];
    	for(int i = 0; i < bitmap.getHeight(); i++)
    	{
    		for(int j = 0; j < bitmap.getWidth(); j++)
    		{
    			int color = bitmap.getPixel(j, i);
    			buffer[0] = (byte)Color.red(color);
    			buffer[1] = (byte)Color.green(color);
    			buffer[2] = (byte)Color.blue(color);
    			buffer[3] = (byte)Color.alpha(color);
    			imageBuffer.put(buffer);
    		}
    	}
    	imageBuffer.position(0);
    	previousGL.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, bitmap.getWidth(), bitmap.getHeight(), 0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE,imageBuffer);
    	
    	// Clean up
    	bitmap.recycle();
    	Animations.add(new AnimationArray(textures[0]));
    }
    public void loadGLTexture(int D, Context context) {
    	// loading texture
    	//Log.d("ratjug",Integer.toString(D));
    	Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), D);

    	// generate one texture pointer
    	previousGL.glGenTextures(1, textures, 0);
    	// ...and bind it to our array
    	previousGL.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

    	// create nearest filtered texture
    	previousGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
    	previousGL.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

    	// Use Android GLUtils to specify a two-dimensional texture image from our bitmap
    	GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    	ByteBuffer imageBuffer = ByteBuffer.allocateDirect(bitmap.getHeight() * bitmap.getWidth() * 4);
    	imageBuffer.order(ByteOrder.nativeOrder());
    	byte buffer[] = new byte[4];
    	for(int i = 0; i < bitmap.getHeight(); i++)
    	{
    		for(int j = 0; j < bitmap.getWidth(); j++)
    		{
    			int color = bitmap.getPixel(j, i);
    			buffer[0] = (byte)Color.red(color);
    			buffer[1] = (byte)Color.green(color);
    			buffer[2] = (byte)Color.blue(color);
    			buffer[3] = (byte)Color.alpha(color);
    			imageBuffer.put(buffer);
    		}
    	}
    	imageBuffer.position(0);
    	previousGL.glTexImage2D(GL10.GL_TEXTURE_2D, 0, GL10.GL_RGBA, bitmap.getWidth(), bitmap.getHeight(), 0, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE,imageBuffer);
    	
    	// Clean up
    	bitmap.recycle();
    	mDraws.add(new DrawArray( textures[0]));
    	textureSize++;
    }
    public void display(GL10 gl){
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glFrontFace(GL10.GL_CW);
    	for(int m=0;m<mDraws.size();m++)
    		mDraws.get(m).display(gl);
    	for(int m=0;m<Animations.size();m++)
    		Animations.get(m).display(gl);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
    public class DrawArray extends ArrayList<Drawable>{
    	public DrawArray(int t){
    		super();
    		mTex = t;
    	}
    	int mTex;
    	float[] mColour;
    	public void display(GL10 gl){
    		//Log.d("ratjug display",Integer.toString(mTex));
            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTex);
            // Point to our buffers
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, Square.textureBuffer);
         
            for(int m=0;m<size();m++){
                get(m).draw(gl);
            }
            //Disable the client state before leaving
    		
        }
    }
    public class AnimationArray extends ArrayList<Drawable>{
    	public AnimationArray(int t){
    		super();
    		mTex = t;
    	}
    	int mTex;
    	public int itr=1;
    	public int dir=1;
    	float[] mColour;
    	public void display(GL10 gl){
    		
            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTex);
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, Square.thirdBuffers[itr]);
            
            for(int m=0;m<size();m++){
            	//Log.d("ratjug display",Integer.toString(mTex));
                get(m).draw(gl);
            }
            //Disable the client state before leaving
    		
    		if(itr==2||itr==0)
    			dir*=-1;
    		itr+=dir;
        }
    }
}
