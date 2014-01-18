package com.example.gca2014;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Looper;
import android.text.method.DateKeyListener;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

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

	public objects.Character lyden;



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
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarebase, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square1low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square2low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square3low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square4low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square5low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square6low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square7low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square8low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square9low, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square1high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square2high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square3high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square4high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square5high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square6high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square7high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square8high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.square9high, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarenormalhigh, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarenormalmid, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarenormallow, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarestonehigh, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarestonelow, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass1, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass2, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass3, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass4, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass5, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squaregrass6, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarewater1, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarewater2, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarewater3, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarewater4, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.squarewater5, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.spriteside2, context);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.spriteback, context);
		
		/*
    	//context.mRenderer.loadGLTexture(com.example.astrobridge.R.drawable.astronaut, context);
    	context.mRenderer.blitGLTexture(com.example.astrobridge.R.drawable.astronauts, context);
		 */
		t1.start();
		ready=true;
	}

	Panel self = this;

	int height = 10;
	int width = 18;
	List<ArrayList<Square>> view = new ArrayList<ArrayList<Square>>();
	List<ArrayList<Square>> maze = new ArrayList<ArrayList<Square>>();
	Thread t1 = new Thread(new Runnable(){
		@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
		public void run()
		{
			for(int i=0;i<height;++i)
			{
				maze.add(new ArrayList<Square>());
				for(int j=0;j<width;++j)
				{
					maze.get(i).add(new Square(i-2,j-4,(int) (19+Math.random()*3)));
				}
			}
			Square position = maze.get(4-3).get(4);
			lyden = new objects.Character(position,36);
			position.setVisible(true);
			/*
			maze.get(4-2).get(3).setVisible(true);
			maze.get(4-2).get(3).setTextureIndex(1);
			maze.get(4-2).get(4).setVisible(true);
			maze.get(4-2).get(4).setTextureIndex(2);
			maze.get(4-2).get(5).setVisible(true);
			maze.get(4-2).get(5).setTextureIndex(3);
			maze.get(4-3).get(3).setVisible(true);
			maze.get(4-3).get(3).setTextureIndex(4);
			maze.get(4-3).get(4).setVisible(true);
			maze.get(4-3).get(4).setTextureIndex(5);
			maze.get(4-3).get(5).setVisible(true);
			maze.get(4-3).get(5).setTextureIndex(6);
			maze.get(4-4).get(3).setVisible(true);
			maze.get(4-4).get(3).setTextureIndex(7);
			maze.get(4-4).get(4).setVisible(true);
			maze.get(4-4).get(4).setTextureIndex(8);
			maze.get(4-4).get(5).setVisible(true);
			maze.get(4-4).get(5).setTextureIndex(9);
			*/
			/*
			maze.get(4-2).get(3).setVisible(true);
			maze.get(4-2).get(3).setTextureIndex(10);
			maze.get(4-2).get(4).setVisible(true);
			maze.get(4-2).get(4).setTextureIndex(11);
			maze.get(4-2).get(5).setVisible(true);
			maze.get(4-2).get(5).setTextureIndex(12);
			maze.get(4-3).get(3).setVisible(true);
			maze.get(4-3).get(3).setTextureIndex(13);
			maze.get(4-3).get(4).setVisible(true);
			maze.get(4-3).get(4).setTextureIndex(14);
			maze.get(4-3).get(5).setVisible(true);
			maze.get(4-3).get(5).setTextureIndex(15);
			maze.get(4-4).get(3).setVisible(true);
			maze.get(4-4).get(3).setTextureIndex(16);
			maze.get(4-4).get(4).setVisible(true);
			maze.get(4-4).get(4).setTextureIndex(17);
			maze.get(4-4).get(5).setVisible(true);
			maze.get(4-4).get(5).setTextureIndex(18);
			*/
			
			resetView();
			context.mRenderer.addDrawable(lyden);
		}
	});

	int dragStartX = 0;
	int dragStartY = 0;
	int startX = 0;
	int startY = 0;
	int viewX = 0;
	int viewY = 0;
	int viewHeight = 5;
	int viewWidth = 9;

	public void resetView(){
		for(int i = 0; i<35;i++){
			context.mRenderer.clear(i);
		}
		view.clear();
		for(int i=-2;i<3;++i)
		{
			view.add(new ArrayList<Square>());
			for(int j=-4;j<5;++j)
			{
				Square square = new Square(maze.get(i+2+viewY).get(j+4+viewX),i,j);
				view.get(i+2).add(square);

				context.mRenderer.addDrawable(square);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		if(e.getAction()==MotionEvent.ACTION_DOWN)
		{
			dragStartX = (int)e.getX();
			dragStartY = (int)e.getY();
			startX = (int)(e.getX()*9f/self.getWidth());
			startY = (int)(e.getY()*5f/self.getHeight());
		}
		else if(e.getAction()==MotionEvent.ACTION_UP)
		{

			int endX = (int)(e.getX()*9f/self.getWidth());
			int endY = (int)(e.getY()*5f/self.getHeight());
			if((startX != endX || startY != endY)){
				double theta = Math.atan2((e.getY()-dragStartY),(e.getX()-dragStartX));
				Log.d("",""+(lyden.getY())+";"+viewY);
				if(theta<Math.PI/4&&theta>=-Math.PI/4){
					if(lyden.getX()+1==viewWidth-1&&viewX+viewWidth<width)
					{
						viewX+=1;
						resetView();
					}
					else if(lyden.getX()<viewWidth-1) {
						
						lyden.move(1,0);
					}
					
				}
				else if(theta<Math.PI*3/4&&theta>=Math.PI/4){
					if(lyden.getY()+1==viewHeight-1&&viewY>0)
					{
						viewY-=1;
						resetView();
					}
					else if(lyden.getY()<viewHeight-1) {
						
						lyden.move(0,1);
					}
				}
				else if(theta<-Math.PI*1/4&&theta>=-Math.PI*3/4){
					if(lyden.getY()-1==0&&viewY+viewHeight<height)
					{
						viewY+=1;
						resetView();
					}
					else if(lyden.getY()>0) {
						
						lyden.move(0,-1);
					}
				}
				else {
					if(lyden.getX()-1==0&&viewX>0)
					{
						viewX-=1;
						resetView();
					}
					else if(lyden.getX()>0) {
						
						lyden.move(-1,0);
					}
				}

			}
			else
			{
				int dx = lyden.getX()-startX;
				int dy = lyden.getY()-startY;
				if((dx==1||dx==-1)&&dy==0)
				{
					Log.d("click","x");
					maze.get(4-startY+viewY).get(startX+viewX).setVisible(true);

					context.mRenderer.clear(lyden.textureIndex());
					lyden.setTextureIndex(35);
					context.mRenderer.addDrawable(lyden);
				}
				else
					if((dy==1||dy==-1)&&dx==0)
					{
						maze.get(4-startY+viewY).get(startX+viewX).setVisible(true);
						
						context.mRenderer.clear(lyden.textureIndex());
						lyden.setTextureIndex(35);
						context.mRenderer.addDrawable(lyden);
					}
				resetView();
			}
		}
		return true;

	}

}
