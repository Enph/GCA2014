package spells;

import objects.Creature;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Water extends Spell{

	public Water(){
		super("Water",10,10);
	}
	
	@Override
	public void onClick(Square cast, Panel panel) {
		if(cast.getObject().getName()=="Flame"){
			cast.setObject(null);
		}
		if(cast.getObject().getName() == "Creature"){
			Creature monster = (Creature)(cast.getObject());
			monster.
		}
	}
	
}
