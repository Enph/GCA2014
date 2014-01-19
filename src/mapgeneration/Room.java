package mapgeneration;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Room extends ArrayList<Square> {
	int x;
	int y;
	int width;
	int height;
	public Room(){
		this.x = (int)(Math.random()*20);
		this.y = (int)(Math.random()*20);
		this.width = (int)(3+3*Math.random());
		this.height = (int)(3+3*Math.random());
	}
	public Room(int x, int y,int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public boolean interferes(int i, int j, int w, int h){
		if(x-1<i+w&&x-1>=i){
			return true;
		}
		else if(x+width>=i&&x+width<i+w){
			return true;
		}
		else if(y-1<j+h&&y-1>=j){
			return true;
		}
		else if(y+height>=j&&y+height<j+h){
			return true;
		}
		return false;
	}
	public boolean within(int i, int j){
		return x-i<width&&x>=i&&y-j<height&&y>=j;
	}
	public boolean generate(List<Room> rooms,Panel panel){
		if(y+height>=panel.maze.size()||x+width>=panel.maze.get(0).size()){
			return false;
		}
		for(Room room:rooms)
		{
			if(this.interferes(room.x, room.y, room.width, room.height)){
				return false;
			}
		}
		for(int i=y;i<y+height;++i)
		{
			for(int j=x;j<x+width;++j)
			{
				Square square = panel.maze.get(i).get(j);
				add(square);
			}
		}
		return true;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
