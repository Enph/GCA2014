package mapgeneration;

import java.util.List;

import objects.Crystal;
import objects.Pedestal;
import objects.Player;
import objects.Shades;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Basement extends Room {
	
	private Pedestal pedestals;
	private Player lyden;
	private Crystal crystal[] = {new Crystal(1),new Crystal(2)};
	private Shades shades[] = {new Shades("Red",10),new Shades("Orange",10),new Shades("Yellow",10),
								new Shades("Green",10),new Shades("Blue",10),new Shades("Purple",10)};
	
	public Basement(){
		super(0,0,13,7);
	}
	
	public boolean generate(Panel panel){
		for(int i=y;i<y+height;++i)
		{
			for(int j=x;j<x+width;++j)
			{
				Square square = panel.maze.get(i).get(j);
				square.setVisible(true);
				add(square);
			}
		}
		
		panel.maze.get(3).get(1).setObject(pedestals);
		panel.maze.get(4).get(3).setObject(pedestals);
		panel.maze.get(5).get(5).setObject(pedestals);
		panel.maze.get(5).get(7).setObject(pedestals);
		panel.maze.get(4).get(9).setObject(pedestals);
		panel.maze.get(3).get(11).setObject(pedestals);
		panel.maze.get(2).get(6).setObject(pedestals);
		
		panel.maze.get(3).get(1).setObject(shades[0]);
		panel.maze.get(4).get(3).setObject(shades[1]);
		panel.maze.get(5).get(5).setObject(shades[2]);
		panel.maze.get(5).get(7).setObject(shades[3]);
		panel.maze.get(4).get(9).setObject(shades[4]);
		panel.maze.get(3).get(11).setObject(shades[5]);
		
		panel.maze.get(3).get(6).setObject(crystal[1]);
		panel.maze.get(0).get(6).setObject(crystal[2]);
		
		lyden = new Player(6,2,48);
		
		return true;
	}
	
	
	
}
