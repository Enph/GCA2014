package mapgeneration;

import java.util.List;

import objects.Crystal;
import objects.Pedestal;
import objects.Player;
import objects.Shades;

import android.util.Log;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Basement extends Room {
	
	private Pedestal pedestals;
	private Player lyden;
	private Crystal crystal[] = {new Crystal(true,3),new Crystal(false,4)};
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
		
		panel.maze.get(1).get(3).setObject(new Pedestal(false));
		panel.maze.get(2).get(4).setObject(new Pedestal(false));
		panel.maze.get(3).get(5).setObject(new Pedestal(false));
		panel.maze.get(3).get(7).setObject(new Pedestal(false));
		panel.maze.get(2).get(8).setObject(new Pedestal(false));
		panel.maze.get(1).get(9).setObject(new Pedestal(false));
		panel.maze.get(1).get(6).setObject(new Pedestal(true));
		
		panel.maze.get(2).get(3).setObject(shades[0]);
		panel.maze.get(3).get(4).setObject(shades[1]);
		panel.maze.get(4).get(5).setObject(shades[2]);
		panel.maze.get(4).get(7).setObject(shades[3]);
		panel.maze.get(3).get(8).setObject(shades[4]);
		panel.maze.get(2).get(9).setObject(shades[5]);
		
		panel.maze.get(3).get(6).setObject(crystal[0]);
		panel.maze.get(0).get(6).setObject(crystal[1]);
		
		panel.reputPlayer(6, 2);
		
		return true;
	}
	
	
	
}
