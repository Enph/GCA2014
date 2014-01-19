package spells;

import objects.Creature;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Slash extends Spell{

	public Slash() {
		super("Slash", 1, 10);
	}
	
	public void onClick(Square cast, Panel panel){
		if(cast.getObject().breakable() == true){
			cast.setObject(null);
		}
		if(cast.getObject().getName()=="Creature"){
			//get the creature clicked on from panel
			Creature monster = (Creature)(cast.getObject());
			int hp = monster.damaged();
			if(hp == 0){
				cast.setObject(null);
			}
		}
	}

}