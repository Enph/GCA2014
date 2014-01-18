package com.example.gca2014;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 *
 * @author Geoff
 */
@TargetApi(3)
public class Panel extends GLSurfaceView implements
	  SurfaceHolder.Callback {
	public int BridgeSteps=0;
	public int lvlcount=1;
	public boolean ready=false;
    public MainActivity context;
   // public Pieces FallingPieces=new Pieces(this);
    public double chanceOfBomb = 0.1f;
    public double chanceOfPowerUp = 0.1f;
    public double chanceOfObstacle = 0.1f;
    public double chanceOfAsteroid = 0.01f;
    public boolean initialized;
    public int leftbound=-10,rightbound=10,topbound=8,botbound=-8;
    Random rnd = new Random();
    public boolean running=true;
    
    
    
    @TargetApi(3)
	public Panel(MainActivity C){
        super(C);
        context = C;
        // adding the callback (this) to the surface holder to intercept events
	  getHolder().addCallback(this);
// make the GamePanel focusable so it can handle events
	  setFocusable(true);
	  
    }
   
    
    public void setup(){
    	int[]testx = {4},testy={4};
		
    	
    	context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square, context);
    	/*
    	//context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.astronaut, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronauts, context);
    	*/
    	t1.start();
    	ready=true;
    }
    
    Panel self = this;

	List<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
    Thread t1 = new Thread(new Runnable(){
    	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
		public void run()
    	{
    		for(int i=-2;i<3;++i)
    		{
    			squares.add(new ArrayList<Square>());
    			for(int j=-4;j<5;++j)
    			{
    				squares.get(i+2).add(new Square(i,j,0, self));

    	    		context.mRenderer.addDrawable(squares.get(i+2).get(j+4));
    			}
    		}
    		
    	}
    });
    
    public boolean onTouchEvent(MotionEvent e)
	{
    	
    	int x = (int)(e.getX()*9f/self.getWidth());
    	int y = (int)(e.getY()*5f/self.getHeight());
    	squares.get(y).get(x);
		return initialized;
	}
    
}
