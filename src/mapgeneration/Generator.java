package mapgeneration;

import java.util.ArrayList;
import java.util.List;

import objects.Obj;
import objects.Wall;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Generator {
	private int texture = 0;
	private int size = 1;
	private int max = 5;
	public Generator(int texture, int size){
		this.texture = texture;
		this.size = size;
	}
	public Generator(int texture, int size, int max){
		this.texture = texture;
		this.size = size;
		this.max = max;
	}
	public List<Room> generateBasement(Panel panel){
		List<Room> rooms = new ArrayList<Room>();
		List<Hall> halls = new ArrayList<Hall>();
		rooms.add(new Basement());
		rooms.get(0).generate(rooms, panel);
		makeWalls(rooms,halls,panel);
		return rooms;
	}
	public List<Room> generateBlackRoom(Panel panel){
		List<Room> rooms = new ArrayList<Room>();
		List<Hall> halls = new ArrayList<Hall>();
		rooms.add(new BlackRoom());
		rooms.get(0).generate(rooms, panel);
		makeWalls(rooms,halls,panel);
		return rooms;
	}
	public List<Room> generateRandom(Panel panel, List<Obj> mustHaves){

		List<Room> all = new ArrayList<Room>();
		List<Room> rooms = new ArrayList<Room>();
		List<Hall> halls = new ArrayList<Hall>();
		while(rooms.size()<=3){
			all.add(new Room(3,0,3,3));
			for(int i = 0;i<16;++i){
				all.add(new Room());
			}
			for(Room room:all){
				if(room.generate(rooms, panel)){
					rooms.add(room);

				}
			}
			all.clear();
		}
		for(Obj object:mustHaves){
			int itemLocationX, itemLocationY; 
			do{
			Room hasItem = rooms.get((int) ((rooms.size()-1)*Math.random())+1);
			itemLocationX = (int) (hasItem.getX()+hasItem.getWidth()*Math.random());
			itemLocationY = (int) (hasItem.getY()+hasItem.getHeight()*Math.random());
			
			}while(panel.maze.get(itemLocationY).get(itemLocationX)==null||panel.maze.get(itemLocationY).get(itemLocationX).isObstacle());
			panel.maze.get(itemLocationY).get(itemLocationX).setObject(object);
		}
		panel.maze.get(2).get(4).setObject(mustHaves.get(0));
		for(int i=0;i<rooms.size();++i){
			Room room = rooms.get(i);
			int tries = (int) (Math.random()+2);

			while(tries>0)
			{
				int select=0;
				do{
					select=(int) (rooms.size()*Math.random());
				}while(select==i);
				Hall hall = new Hall(room,rooms.get(select));
				if(hall.generate(rooms, panel)){
					halls.add(hall);
					--tries;
				}
			}
		}

		makeWalls(rooms,halls,panel);
		return rooms;
	}
	public void makeWalls(List<Room> rooms,List<Hall> halls,Panel panel){

		List<Square> walls = new ArrayList<Square>();
		List<Integer[]> wallet = new ArrayList<Integer[]>();
		for(int i=0;i<panel.maze.size();++i){
			for(int j=0;j<panel.maze.get(i).size();++j){
				Square square = panel.maze.get(j).get(i);
				boolean isWall = true;
				for(Room room:rooms){
					if(room.contains(square)){
						isWall = false;
						break;
					}
				}
				for(Hall hall:halls){
					if(hall.contains(square)){
						isWall = false;
						break;
					}
				}
				//Log.d("things","j"+j+"i"+i);
				if(isWall){
					square.obstacle();
					square.setTextureIndex(texture+(int)(max*Math.random()));
					square.setTextureSize(size);
					//if(j-1>=0)Log.d("things",panel.maze.get(j-1).get(i).isObstacle()?"make wall":"dont make wall");
					walls.add(square);
					wallet.add(new Integer[]{j,i});
				}
			}
		}
		for(int i=0;i<walls.size();++i){
			if(wallet.get(i)[0]-1>=0&&!panel.maze.get(wallet.get(i)[0]-1).get(wallet.get(i)[1]).isObstacle()){
				if(wallet.get(i)[1]-1>=0&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]-1).isObstacle())
				{
					walls.get(i).setObject(new Wall(6));
				}
				else if(wallet.get(i)[1]+1<20&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]+1).isObstacle())
				{
					walls.get(i).setObject(new Wall(8));
				}
				else {
					walls.get(i).setObject(new Wall(7));
				}
			}
			else if(wallet.get(i)[0]+1<20&&!panel.maze.get(wallet.get(i)[0]+1).get(wallet.get(i)[1]).isObstacle()){
				if(wallet.get(i)[1]-1>=0&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]-1).isObstacle())
				{
					walls.get(i).setObject(new Wall(1));
				}
				else if(wallet.get(i)[1]+1<20&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]+1).isObstacle())
				{
					walls.get(i).setObject(new Wall(3));
				}
				else {
					walls.get(i).setObject(new Wall(2));
				}
			}
			else if(wallet.get(i)[1]-1>=0&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]-1).isObstacle())
			{
				walls.get(i).setObject(new Wall(5));
			}
			else if(wallet.get(i)[1]+1<20&&!panel.maze.get(wallet.get(i)[0]).get(wallet.get(i)[1]+1).isObstacle())
			{
				walls.get(i).setObject(new Wall(4));
			}
		}
	}
}
