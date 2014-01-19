package mapgeneration;

import java.util.ArrayList;
import java.util.List;

import objects.Wall;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Generator {
	public List<Room> generate(Panel panel){
		List<Room> all = new ArrayList<Room>();
		List<Room> rooms = new ArrayList<Room>();
		List<Hall> halls = new ArrayList<Hall>();
		List<Square> walls = new ArrayList<Square>();
		List<Integer[]> wallet = new ArrayList<Integer[]>();
		all.add(new Room(3,0,3,3));
		for(int i = 0;i<16;++i){
			all.add(new Room());
		}
		for(Room room:all){
			if(room.generate(rooms, panel)){
				rooms.add(room);
			}
		}
		for(Room room:rooms){
			int tries = (int) (Math.random()+2);

			while(tries>0)
			{
				Hall hall = new Hall(room,rooms.get((int) (rooms.size()*Math.random())));
				if(hall.generate(rooms, panel)){
					halls.add(hall);
					--tries;
				}
			}
		}
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
					square.setTextureIndex(34+(int)(4*Math.random()));
					//if(j-1>=0)Log.d("things",panel.maze.get(j-1).get(i).isObstacle()?"make wall":"dont make wall");
					walls.add(square);
					wallet.add(new Integer[]{j,i});
				}
			}
		}
		for(int i=0;i<walls.size();++i){
			if(wallet.get(i)[0]-1>=0&&!panel.maze.get(wallet.get(i)[0]-1).get(wallet.get(i)[1]).isObstacle()){
				//Log.d("at","i"+i+"j"+j);
				walls.get(i).setObject(new Wall(44));
			}
			//Log.d("here",""+square.getY()+1+"  "+square.getX());
			else if(wallet.get(i)[0]+1<20&&!panel.maze.get(wallet.get(i)[0]+1).get(wallet.get(i)[1]).isObstacle()){
				
				walls.get(i).setObject(new Wall(39));
			}
		}
		return rooms;
	}
}
