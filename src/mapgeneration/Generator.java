package mapgeneration;

import java.util.ArrayList;
import java.util.List;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Generator {
	public List<Room> generate(Panel panel){
		List<Room> all = new ArrayList<Room>();
		List<Room> rooms = new ArrayList<Room>();
		all.add(new Room(3,0,3,3));
		for(int i = 0;i<16;++i){
			all.add(new Room());
		}
		for(Room room:all){
			if(room.generate(rooms, panel)){
				rooms.add(room);
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
				if(isWall){
					square.obstacle();
					square.setTextureIndex(34+(int)(4*Math.random()));
				}
			}
		}
		return rooms;
	}
}
