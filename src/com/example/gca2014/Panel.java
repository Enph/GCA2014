package com.example.gca2014;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.TargetApi;
import android.opengl.GLSurfaceView;
import android.os.Looper;
import android.util.Log;
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
    public LevelSettings currentLevel=new LevelSettings("pluto");
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
    
    Thread t1 = new Thread(new Runnable(){
    	public void run()
    	{
    		List<ArrayList<Square>> squares = new ArrayList<ArrayList<Square>>();
    		for(int i=-2;i<3;++i)
    		{
    			squares.add(new ArrayList<Square>());
    			for(int j=-4;j<5;++j)
    			{
    				squares.get(i+2).add(new Square(i,j,0, self));

    	    		context.mRenderer.addDrawable(squares.get(i+2).get(j+4));
    			}
    		}
    		for(int i=0;i<8;++i)
    		{
    			synchronized(context)
    			{
    				//squares.get(i+8).get(4).setVisible(true);
    			}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
    		}
    	}
    });
    
    
}
