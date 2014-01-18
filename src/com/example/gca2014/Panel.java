package com.example.astrobridge;


import java.util.ArrayList;
import java.util.Random;

import Astronaut.Astronaut;
import Astronaut.Astronauts;
import Astronaut.Builder;
import Piece.Asteroid;
import Piece.Asteroids;
import Piece.Piece;
import Piece.Pieces;
import Square.Bomb;
import Square.Powerup;
import Square.Square;
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
    public Pieces Bridge=new Pieces(this);
    public Pieces FlyingPieces=new Pieces(this);
   // public Pieces FallingPieces=new Pieces(this);
    public Astronauts people=new Astronauts(this);
    public Asteroids ast=new Asteroids(this);
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
		
    	
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.square7, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.bombsquare7, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.obssquare7, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupbuildsquare7, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.pupjetsquare7, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare1, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare2, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare3, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare4, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare5, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare6, context);
    	context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.puplazersquare7, context);
    	//context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.astronaut, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronauts, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsbuild1, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsbuild2, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsbuild3, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsjet1, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsjet2, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautsjet3, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautslazer1, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautslazer2, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronautslazer3, context);
    	t3.start();
    	t2.start();
    	t1.start();

    	ready=true;
    }
    public Piece generatePieceLeftHand()
    {
    	if(rnd.nextInt(100)<currentLevel.asteriodpercent)
    		return new Asteroid ((int)(Math.random()*7), 7-(int)(Math.random()*2), this);
    	else
        	return Piece.Possible[(int)(Math.random()*7)].make(5+(int)(Math.random()*3),-12+(int)(Math.random()*2), this,1,0);
    }
    public Piece generatePieceRightHand()
    {
    	if(rnd.nextInt(100)<currentLevel.asteriodpercent)
    		return new Asteroid ((int)(Math.random()*7), 7+(int)(Math.random()*2), this);
    	else
        	return Piece.Possible[(int)(Math.random()*7)].make(5+(int)(Math.random()*3),12-(int)(Math.random()*2), this, -1,0);
    }
    Thread t2 = new Thread(new Runnable()
    {
    	public void run()
    	{
    		while(running)
    		{
    			synchronized(context)
    			{
    				FlyingPieces.moveFlyingPieces();

    				for(int m=0;m<FlyingPieces.size();m++)
    				{
    					for(int n=0;n<FlyingPieces.get(m).size();n++)
    					{
    						if(FlyingPieces.get(m).get(n).getY()<=-10 ||FlyingPieces.get(m).get(n).getY()>=10){
    							FlyingPieces.get(m).clear();
    							FlyingPieces.remove(m);
    							if(m>0)
    								m--;
    							break;
    						}
    					}
    				}
    			}
    			try
    			{
    				t2.sleep(1000);
    			}
    			catch(Exception e){}
    		}
    		
    	}
    });
    
    Thread t3=new Thread(new Runnable()
    {
    	private double adder=1f;
    	public void run()
    	{
    		try
    		{
    		t3.sleep(500);
    		}
    		catch(Exception e){}
    		while(running)
    		{
    			synchronized(context)
    			{
    				if(Math.random()<adder)
    				{
    					if(Math.random()<.5f)
    						FlyingPieces.add(generatePieceLeftHand());
    					else
    						FlyingPieces.add(generatePieceRightHand());
    					adder=0f;
    				} 
    				else 
    				{
    					adder+=0.1f;
    				}
    			}
    			try
    			{
    				t3.sleep((int)(2500/currentLevel.flyblockspawnrate)); //lower fly block spawn rates means less blocks for the bridge and hence harder levels
    			}														 
    			catch(Exception e){}
    		}
    	}
    });
    
    
    public Panel getPan(){
    	return this;
    }
    Thread t1 = new Thread(new Runnable(){
    	private float adder=1.0f;
    	private boolean waiting=false;
    	public void run() {
    		Bridge.add(Piece.BridgePiece.make(-8, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-7, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-6, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-5, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-4, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-3, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-2, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-1, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(-0, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(8, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(7, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(6, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(5, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(4, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(3, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(2, -3, getPan(),0,-1));
    		Bridge.add(Piece.BridgePiece.make(1, -3, getPan(),0,-1));
    		/*
    		people.add(new Astronaut(-1,-12,getPan(),0));
    		people.add(new Astronaut(-2,-12,getPan(),0));
    		people.add(new Astronaut(-3,-12,getPan(),0));
    		people.add(new Astronaut(0,-12,getPan(),0));
    		people.add(new Astronaut(1,-12,getPan(),0));
    		people.add(new Astronaut(2,-12,getPan(),0));
    		people.add(new Astronaut(3,-12,getPan(),0));*/
    		people.add(new Builder(-7,1,getPan()));
    		people.get(0).tier=3;
    		people.add(new Builder(-7,-0,getPan()));
    		people.get(0).tier=2;
    		people.add(new Builder(-7,-1,getPan()));
    		while(running){    			
    			synchronized(context)
    			{    				
    				Bridge.moveBridgePieces(); //moves all the pieces down, astronauts 'move up'.
    				for(int m=0;m<Bridge.size();m++)
    				{
    					for(int n=0;n<Bridge.get(m).size();n++)
    					{
    						if(Bridge.get(m).get(n).getX()<=-12){
    							Bridge.get(m).clear();
    							Bridge.remove(m);
    							if(m>0)
    								m--;
    							break;
    						}
    					}
    				}
    				
    				for(int m=0;m<people.size();m++) //kills them if walk or land into obstacle
    				{
    					if(Bridge.getObstacle(people.get(m))!=null &&people.get(m).getG()==true)
    						people.remove(people.get(m));
    				}
    				
    				
    				for(int m=0;m<people.size();m++) //lands the astronauts if still in midair
    				{
    					people.get(m).land();
    				}
    				
    				for(int m=0;m<ast.size();m++) //crashes asteroids
    				{
    					for(int n=0;n<Bridge.size();n++)
    					{
    						if(ast.get(m).collidesWith(Bridge.get(n)))
    						{
    							getPan().destroyAsteroid(ast.get(m));
    							Bridge.remove(Bridge.get(n));
    							break;
    						}
    					}
    				}
    				if(people.size()==0){
    					if(initialized)
    					Log.v("GAME OVER","you lose!");
    				}
    				for(int m=0;m<people.size();m++) //kills the ones who walk/land into a hole
    				{
    					if(!(Bridge.hasSquare(people.get(m))) && people.get(m).getG()==true){
    						people.remove(people.get(m));
    						Log.d("DIE DIE DIE","DIE DIE DIE");
    					}
    				}
    				for(int m=0;m<people.size();m++) //activates their power if applicable
    				{
    					people.get(m).power();
    				}
    				

    				
  
    				ArrayList<Bomb> explodeBombs = Bridge.bombsThatExplode();
    				for(int m=0;m<explodeBombs.size();m++) //kills them if walked or landed into a bomb
    				{
    					if(explodeBombs.get(m)!=null){
    						Astronaut victim=people.proximity(explodeBombs.get(m));
    						if(victim!=null)
    							people.remove(victim);
    						Bridge.deleteSquare(explodeBombs.get(m));
    						}
    					}
    				}
    				
    				for(int m=0;m<people.size();m++) //gets a powerup if the astronaut was in the air
    				{
    					Powerup pu=Bridge.getPowerup(people.get(m));
    					if(pu!=null && people.get(m).getG()==false)
    					{
    						pu.triggerUpgrade(people.get(m));
    						pu.used();
    						
    					}
    				}
    			
    				//Bridge.add(Piece.BridgePiece.make(-3, 13, getPan(), 0, -1));
    			try{t1.sleep((int)(2000/currentLevel.bridgespeed));
    			BridgeSteps++;
    			if(BridgeSteps>=currentLevel.length){
    				lvlcount++;
    				if(lvlcount==2)
    				currentLevel=new LevelSettings("neptune");
    				if(lvlcount==3)
    					currentLevel=new LevelSettings("uranus");
    				if(lvlcount==4)
    					currentLevel=new LevelSettings("saturn");
    				if(lvlcount==5)
    					currentLevel=new LevelSettings("jupiter");
    				if(lvlcount==6)
    					currentLevel=new LevelSettings("mars");
    				if(lvlcount==7)
    					currentLevel=new LevelSettings("earth");
    				if(lvlcount==8)
    					currentLevel=new LevelSettings("venus");
    				if(lvlcount==9)
    					currentLevel=new LevelSettings("mercury");
    				if(lvlcount==10)
    					currentLevel=new LevelSettings("sun");
    				
    			}
    			//currentlevel.bridgespeed is a field that's value increases as level difficulty increases
    			//ie the bridge moves faster and faster as the difficulty goes up.
    		
    			}catch(Exception e){}

    			
			}
    		
    	}});
	public boolean hasAsteroid() 
	{
		return (ast.size()>0);
	}

	public void destroyAsteroid(Asteroid a) 
	{
		if(ast.contains(a))
			ast.remove(a);
	}
	
	public void destroyAsteroid()
	{
		destroyAsteroid(ast.get(0));
	}
	
	@TargetApi(12)
	public boolean onTouchEvent(MotionEvent e)
	{
		float yfactor = 1f/17f;
		float xfactor = 1f/21f;
		int h=this.getMeasuredHeightAndState(),w=this.getMeasuredWidthAndState();
		int xshift = w/2, yshift = h/2;
		float tapx = (e.getAxisValue(MotionEvent.AXIS_X)-xshift)/GLRenderer.mWidth,tapy = -(e.getAxisValue(MotionEvent.AXIS_Y)-yshift)/GLRenderer.mHeight;
		//Log.d("TAP LOCATION:" ,Float.toString(tapx)+" "+Float.toString(tapy));
		boolean breaker = false;
		switch(e.getAction())
		{
		case MotionEvent.ACTION_DOWN:
		{
			try{
			if(tapy>-10*yfactor)
			{
			for(int m=0;m<FlyingPieces.size();m++)
			{
				for(int n=0;n<FlyingPieces.get(m).size();n++)
				{
					float xmin=FlyingPieces.get(m).get(n).getY()*yfactor-yfactor/2,xmax=FlyingPieces.get(m).get(n).getY()*yfactor+yfactor/2;
					float ymin=FlyingPieces.get(m).get(n).getX()*xfactor-xfactor/2,ymax=FlyingPieces.get(m).get(n).getX()*xfactor+xfactor/2;
					//Log.d("coords",Float.toString(xmin)+","+xmax+"),("+Float.toString(ymin)+","+ymax);
					if(tapx>xmin&&tapx<=xmax&&tapy>ymin&&tapy<=ymax)
					{
						if(tapx>-4f*yfactor && tapx<4f*yfactor)
						{
							FlyingPieces.startFalling(m);
							breaker=true;
							break;
						}
						else
						{
							FlyingPieces.get(m).rotate();	
							breaker=true;
							break;
						}
					}
					
					
				}
				if(breaker)
					break;
			}
			}
			if(tapy<=-10*xfactor)
				{
					for(int m=0;m<people.size();m++)
					{
						people.get(m).jump();
					}
				}
			}
			catch(Exception E){
				
			}
		}
		}
		return true;
	}
    	
}
