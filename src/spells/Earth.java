package spells;

import objects.Boulder;
import objects.Creature;
import objects.Player;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Earth extends Spell{

	public Earth(){
		super("Earth",4,10);
	}
	
	public void onClick(Square cast, Panel panel) {
		Boulder boulder = new Boulder();
		Player lyden = panel.getLyden();
		if(lyden.getFacing()==1){
			if(cast.getObject()!=null&&cast.getObject().getName() == "Creature"){
				Creature monster = (Creature)(cast.getObject());
				monster.move(0, 1, panel);
			}
			cast.setObject(boulder);
		}
		else if(lyden.getFacing()==2){
			if(cast.getObject()!=null&&cast.getObject().getName() == "Creature"){
				Creature monster = (Creature)(cast.getObject());
				monster.move(1, 0, panel);
			}
			cast.setObject(boulder);
		}
		else if(lyden.getFacing()==3){
			if(cast.getObject()!=null&&cast.getObject().getName() == "Creature"){
				Creature monster = (Creature)(cast.getObject());
				monster.move(0, -1, panel);
			}
			cast.setObject(boulder);
		}
		else{
			if(cast.getObject()!=null&&cast.getObject().getName() == "Creature"){
				Creature monster = (Creature)(cast.getObject());
				monster.move(-1, 0, panel);
			}
			cast.setObject(boulder);
		}
		
		
	}
	
}
