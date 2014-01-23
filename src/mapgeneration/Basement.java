package mapgeneration;

import java.util.List;

import objects.Crystal;
import objects.Pedestal;
import objects.Player;
import objects.Portal;
import objects.Shades;
import spells.Light;
import spells.Slash;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Basement extends Room {
	public static Portal[] portals = {
		new Portal(null,1,true,null,1,1,1),
		new Portal(null,2,true,null,1,0,0),
		new Portal(null,3,true,null,0.75f,0.25f,0.0f),
		new Portal(null,4,true,null,1f,1f,0.0f),
		new Portal(null,5,true,null,0,1,0),
		new Portal(null,6,true,null,0,0,1),
		new Portal(null,7,true,null,0.5f,0,0.5f),
		new Portal(null,8,true,null,0.3f,0.3f,0.3f)};
	private Crystal crystal[] = {new Crystal(true,1,new Light()),new Crystal(false,2,new Slash())};
	private Shades shades[] = {new Shades(2),new Shades(3),new Shades(4),
								new Shades(5),new Shades(6),new Shades(7)};
	
	public Basement(){
		super(0,0,13,7);
	}
	@Override
	public boolean generate(List<Room> rooms,Panel panel){
		for(int i=y;i<y+height+1;++i)
		{
			for(int j=x;j<x+width+1;++j)
			{
				Square square = panel.maze.get(i).get(j);
				square.setVisible(true);
				add(square);
			}
		}
		
		panel.maze.get(1).get(3).setObject(portals[0]);
		panel.maze.get(2).get(4).setObject(portals[1]);
		panel.maze.get(3).get(5).setObject(portals[2]);
		panel.maze.get(3).get(7).setObject(portals[3]);
		panel.maze.get(2).get(8).setObject(portals[4]);
		panel.maze.get(1).get(9).setObject(portals[5]);
		panel.maze.get(1).get(6).setObject(new Pedestal());
		
		panel.maze.get(2).get(3).setObject(shades[0]);
		panel.maze.get(3).get(4).setObject(shades[1]);
		panel.maze.get(4).get(5).setObject(shades[2]);
		panel.maze.get(4).get(7).setObject(shades[3]);
		panel.maze.get(3).get(8).setObject(shades[4]);
		panel.maze.get(2).get(9).setObject(shades[5]);
		
		panel.maze.get(2).get(6).setObject(crystal[0]);
		panel.maze.get(0).get(6).setObject(crystal[1]);
		
		panel.reputPlayer(6, 2);
		
		return true;
	}
	
	
	
}
