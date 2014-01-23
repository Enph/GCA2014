package com.example.gca2014;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import objects.Crystal;
import objects.Flame;
import objects.Obj;
import objects.Pedestal;
import objects.Player;
import objects.Portal;
import objects.Sage;
import objects.Wood;
import spells.Earth;
import spells.Fire;
import spells.Water;

import mapgeneration.Basement;
import mapgeneration.Generator;
import mapgeneration.Room;

import agency.Position;
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
	public static MainActivity context;
	// public Pieces FallingPieces=new Pieces(this);
	public double chanceOfBomb = 0.1f;
	public double chanceOfPowerUp = 0.1f;
	public double chanceOfObstacle = 0.1f;
	public double chanceOfAsteroid = 0.01f;
	public boolean initialized;
	public int leftbound=-10,rightbound=10,topbound=8,botbound=-8;
	Random rnd = new Random();
	public boolean running=true;

	public objects.Player lyden;



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
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.allimages1, context, 64);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.allimages2, context, 64);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.frame, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.allimages3, context, 64);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_1a_lyden_hub, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_1b_nb_hub, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_2a_lyden_white, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_2b_lyden_white, context, 1);/*
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_3a_nb_red, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_3b_nb_red, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_4a_lyden_orange, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_4a_lyden_yellow, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_4b_nb_orange, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_6a_nb_green, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_6b_lyden_green, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_6c_anon_green, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_7a_lyden_blue, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_7b_nb_blue, context, 1);
		context.mRenderer.loadGLTexture(com.example.gca2014.R.drawable.note_8a_lyden_purple, context, 1);*/
		

		lyden = new objects.Player(4,1,62);
		createMazes();
		run(0);
		ready=true;
		context.mRenderer.addDrawable(new Drawable(){

			@Override
			public void draw(GL10 gl) {
				float[] vertices = new float[]{
						-0.075f*9f, -0.075f*5f,  0.005f,		// V1 - bottom left
						-0.075f*9f,  0.075f*5f,  0.005f,		// V2 - top left
						0.075f*9f, -0.075f*5f,  0.005f,		// V3 - bottom right
						0.075f*9f,  0.075f*5f,  0.005f,		// V4 - top right

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
				// TODO Auto-generated method stub
				return 2;
			}});
	}

	Panel self = this;

	int height = 20;
	int width = 20;
	List<ArrayList<Square>> view = new ArrayList<ArrayList<Square>>();
	public List<ArrayList<Square>> maze = new ArrayList<ArrayList<Square>>();
	public List<Position> mazeStartLocations = new ArrayList<Position>();
	public List<List<ArrayList<Square>>> mazes = new ArrayList<List<ArrayList<Square>>>();
	
	public void createMazes(){
		Generator gen = new Generator(0,1);
		List<Obj> mustHaves = new ArrayList<Obj>();
		
		//Basement 52-56
		maze = this.mazesloop(52,56);
		gen.generateBasement(this);
		mazes.add(maze);
		mazeStartLocations.add(new Position(6,2));
		
		//White Room 52-56
		maze = this.mazesloop(52,56);
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,2,true,Basement.portals[1],1,0,0), new Crystal(true,3,null), new Crystal(true,3,null)));
		gen.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Red Room 27-31
		maze = this.mazesloop(27,31);
		//add pressure plate to gain access to certain room
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,3,true,Basement.portals[2],0.75f,0.25f,0), new Crystal(true,4,new Earth()), new Crystal(true,5,null)));
		gen.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Orange Room 57-61 for floors, 4-8 for fire walls
		maze = this.mazesloop(57,61);
		//add pressure plate to gain access to certain room, teddy bear
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,4,true,Basement.portals[3],1,1,0), new Crystal(true,6,null), new Crystal(true,8,null),new Flame()));
		Generator gener = new Generator(4,0);
		gener.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Yellow Room 4-8 sand floors
		maze = this.mazesloop(4,8);
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,5,true,Basement.portals[4],0f,1f,0),new Crystal(true,7,null)));
		gen.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Green Room 33-37
		maze = this.mazesloop(33,37);
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,6,true,Basement.portals[5],0,0,1),new Crystal(true,9,null),new Crystal(true,10,null), new Crystal(true,11,null),new Wood()));
		gen.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Blue Room 57-61 for floors, 0-3 for water
		maze = this.mazesloop(57,61);
		//flowing water u can block with boulder
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,7,true,Basement.portals[6],1,0,1),new Crystal(true,12,null),new Crystal(true,13,new Water())));
		Generator gene = new Generator(0,0,4);
		gene.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Purple Room 38-42
		maze = this.mazesloop(38,42);
		mustHaves.addAll(Arrays.asList(new Portal(null,0,true,null,0.5f,0.5f,0.5f),new Portal(null,8,true,Basement.portals[7],0.25f,0.25f,0.25f),new Crystal(true,14,null), new Sage()));
		gen.generateRandom(this, mustHaves);
		mazes.add(maze);
		mustHaves.clear();
		mazeStartLocations.add(new Position(4,2));
		
		//Black Room
		maze = this.mazesloop(52,56);
		gen.generateBlackRoom(this);
		mazes.add(maze);
		mazeStartLocations.add(new Position(6,2));
	}
	
	public List<ArrayList<Square>> mazesloop(int low, int high){
		maze = new ArrayList<ArrayList<Square>>();
		for(int i=0;i<height;++i)
		{
			maze.add(new ArrayList<Square>());
			for(int j=0;j<width;++j)
			{
				maze.get(i).add(new Square(i-2,j-4,(int) (low+Math.random()*(high-low))));
			}
		}
		return maze;
	}

	public void reputPlayer(int x, int y){

		viewX = x-4;
		viewY = y-2;
		lyden.setX(4);
		lyden.setY(2);
		lyden.reset();

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public void run(int i)
	{
		maze = mazes.get(i);
		reputPlayer(mazeStartLocations.get(i).getX(),mazeStartLocations.get(i).getY());
		resetView();
		context.mRenderer.addDrawable(lyden);
	}

	int dragStartX = 0;
	int dragStartY = 0;
	int startX = 0;
	int startY = 0;
	int viewX = 0;
	int viewY = 0;
	int viewHeight = 5;
	int viewWidth = 8;
	boolean onSpellbook = false;

	public void resetView(){
		synchronized(context){
			for(int i = 0; i<64;i++){
				context.mRenderer.clear(0,i);				
			}
			for(int i = 0; i<50;i++){
				context.mRenderer.clear(1,i);				
			}
			view.clear();
			for(int i=-2;i<3;++i)
			{
				view.add(new ArrayList<Square>());
				for(int j=-4;j<4;++j)
				{
					Square square = new Square(maze.get(i+2+viewY).get(j+4+viewX),i,j);
					view.get(i+2).add(square);
					if(square.getVisible()){
						context.mRenderer.addDrawable(square);
						square.makeOutline();
					}
				}
			}
			lyden.refreshHeart();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		if(ready){
			if(crystal!=null){
				context.mRenderer.clear(crystal.getNote(), 0);
				crystal=null;
			}
			else
			{
				if(e.getAction()==MotionEvent.ACTION_DOWN)
				{
					dragStartX = (int)e.getX();
					dragStartY = (int)e.getY();
					startX = (int)(e.getX()*9f/self.getWidth());
					startY = (int)(e.getY()*5f/self.getHeight());
					if(startX==8){
						onSpellbook = true;
					}
					else {
						onSpellbook = false;
					}
				}
				else if(e.getAction()==MotionEvent.ACTION_UP)
				{

					int endX = (int)(e.getX()*9f/self.getWidth());
					int endY = (int)(e.getY()*5f/self.getHeight());

					if((startX != endX || startY != endY)){
						if(onSpellbook){
							lyden.getSpellbook().scroll(endY-startY);
							
						}
						else {
							double theta = Math.atan2((e.getY()-dragStartY),(e.getX()-dragStartX));


							if(theta<Math.PI/4&&theta>=-Math.PI/4){
								if(lyden.getX()<viewWidth-1 && !view.get(4-lyden.getY()).get(lyden.getX()+1).isObstacle()){
									if(lyden.getX()+1==viewWidth-1&&viewX+viewWidth<width)
									{
										viewX+=1;
										resetView();
									}
									else if(lyden.getX()<viewWidth-1) {
										lyden.move(1,0);
									}
								}
								context.mRenderer.clear(1,lyden.textureIndex());
								lyden.faceLeft();
								context.mRenderer.addDrawable(lyden);
							}
							else if(theta<Math.PI*3/4&&theta>=Math.PI/4){
								if(lyden.getY()<viewHeight-1 && !view.get(4-(lyden.getY()+1)).get(lyden.getX()).isObstacle()){
									if(lyden.getY()+1==viewHeight-1&&viewY>0)
									{
										viewY-=1;
										resetView();
									}
									else if(lyden.getY()<viewHeight-1) {
										lyden.move(0,1);
									}
								}
								context.mRenderer.clear(1,lyden.textureIndex());
								lyden.faceUp();
								context.mRenderer.addDrawable(lyden);
							}
							else if(theta<-Math.PI*1/4&&theta>=-Math.PI*3/4){
								if(lyden.getY()>0 && !view.get(4-(lyden.getY()-1)).get(lyden.getX()).isObstacle()){
									if(lyden.getY()-1==0&&viewY+viewHeight<height)
									{
										viewY+=1;
										resetView();
									}
									else if(lyden.getY()>0) {
										lyden.move(0,-1);
									}
								}
								context.mRenderer.clear(1,lyden.textureIndex());
								lyden.faceDown();
								context.mRenderer.addDrawable(lyden);
							}
							else {
								if( lyden.getX()>0&&!view.get(4-lyden.getY()).get(lyden.getX()-1).isObstacle()){
									if(lyden.getX()-1==0&&viewX>0)
									{
										viewX-=1;
										resetView();
									}
									else if(lyden.getX()>0) {
										lyden.move(-1,0);

									}
								}
								context.mRenderer.clear(1,lyden.textureIndex());
								lyden.faceRight();
								context.mRenderer.addDrawable(lyden);
							}
							if(!view.get(lyden.getY()).get(lyden.getX()).getVisible()){
								lyden.darkness(this);
							}
							maze.get(4-lyden.getY()+viewY).get(lyden.getX()+viewX).onStep(this);
						}
					}
					else
					{
						if(startX==8&&startY==0){
							context.finish();
						}
						int dx = lyden.getX()-startX;
						int dy = lyden.getY()-startY;
						if((dx==1||dx==-1)&&dy==0)
						{
							//maze.get(4-startY+viewY).get(startX+viewX).setVisible(true);
							lyden.cast(maze.get(4-startY+viewY).get(startX+viewX),this);
							lyden.cast(maze.get(4-lyden.getY()+viewY).get(lyden.getX()+viewX),this);
							context.mRenderer.clear(1,lyden.textureIndex());
							if(dx==1){
								lyden.faceRight();
							}
							else{
								lyden.faceLeft();
							}
							context.mRenderer.addDrawable(lyden);
						}
						else
							if((dy==1||dy==-1)&&dx==0)
							{
								lyden.cast(maze.get(4-startY+viewY).get(startX+viewX),this);
								lyden.cast(maze.get(4-lyden.getY()+viewY).get(lyden.getX()+viewX),this);
								context.mRenderer.clear(1,lyden.textureIndex());
								if(dy==1){
									lyden.faceDown();
								}
								else{
									lyden.faceUp();
								}
								context.mRenderer.addDrawable(lyden);
							}
						resetView();
					}
				}
			}
		}
		return true;

	}


	public objects.Player getLyden() {
		return lyden;
	}


	public Square getSquare(int x, int y) {
		return maze.get(4-y+viewY).get(x+viewX);
	}

	Crystal crystal = null;
	public void setOnCrystal(Crystal crystal) {
		this.crystal = crystal;
	}

}
