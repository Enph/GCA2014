package mapgeneration;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Hall extends ArrayList<Square> {
	int ax;
	int ay;
	int bx;
	int by;
	public Hall(Room a, Room b){
		int side = (int) (Math.random()*4);
		if(side==0){
			ax=a.x+a.width;
			ay=(int) (a.y+a.height*Math.random());
		}
		else if(side==1){
			ax=a.x-1;
			ay=(int) (a.y+a.height*Math.random());
		}
		else if(side==2){
			ay=a.y+a.height;
			ax=(int) (a.x+a.width*Math.random());
		}
		else if(side==3){
			ay=a.y-1;
			ax=(int) (a.x+a.width*Math.random());
		}
		side = (int) (Math.random()*4);
		if(side==0){
			bx=b.x+b.width;
			by=(int) (b.y+b.height*Math.random());
		}
		else if(side==1){
			bx=b.x-1;
			by=(int) (b.y+b.height*Math.random());
		}
		else if(side==2){
			by=b.y+b.height;
			bx=(int) (b.x+b.width*Math.random());
		}
		else if(side==3){
			by=b.y-1;
			bx=(int) (b.x+b.width*Math.random());
		}
	}

	public boolean generate(List<Room> rooms,Panel panel){
		if(ay<0||ax<0||by<0||bx<0||ay>=20||ax>=20||by>=20||bx>=20){
			return false;
		}

		int diry = 0;
		if(ay!=by){
			diry= Math.abs(by-ay)/(by-ay);
		}
		int dirx = 0;
		if(ax!=bx){
			dirx= Math.abs(bx-ax)/(bx-ax);
		}
		for(int i=ay;i!=by;i+=diry)
		{
			Square square = panel.maze.get(i).get(ax);
			for(Room room:rooms){
				if(room.within(ax, i)){
					return true;
				}
				

			}
			add(square);
		}
		for(int j=ax;j!=bx;j+=dirx)
		{
			Square square = panel.maze.get(by).get(j);
			for(Room room:rooms){
				if(room.within(by, j)){
					return true;
				}

			}
			add(square);
		}
		add(panel.maze.get(by).get(bx));
		return true;
	}
	public int getX() {
		return ax;
	}
	public int getY() {
		return ay;
	}
}
