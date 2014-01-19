package mapgeneration;

import java.util.List;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

import objects.Player;
import objects.Sage;
import objects.Shades;

public class BlackRoom extends Room{

	private Shades shades[] = {new Shades(10),new Shades(10)};
	private Sage sage = new Sage();
	
	public BlackRoom(){
		super(0,0,13,7);
	}
	
	public boolean generate(List<Room> rooms,Panel panel){
		for(int i=y;i<y+height;++i)
		{
			for(int j=x;j<x+width;++j)
			{
				Square square = panel.maze.get(i).get(j);
				add(square);
			}
		}
		
		panel.maze.get(5).get(6).setObject(sage);
		panel.maze.get(5).get(6).setVisible(true);
		panel.maze.get(5).get(4).setObject(shades[0]);
		panel.maze.get(5).get(4).setVisible(true);
		panel.maze.get(5).get(8).setObject(shades[1]);
		panel.maze.get(5).get(8).setVisible(true);
		
		panel.maze.get(2).get(6).setVisible(true);
		panel.reputPlayer(6, 2);
		
		return true;
	}
	
}
