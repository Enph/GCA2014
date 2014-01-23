package spells;

import objects.Creature;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Fire extends Spell{

	public Fire(){
		super("Fire",2,10);
	}
	
	public void onClick(Square cast, Panel panel) {
		if(cast.getObject()==null){
			return;
		}
		if(cast.getObject().burnable() == true){
			cast.setObject(null);
		}
		if(cast.getObject().getName() == "Creature"){
			Creature monster = (Creature)(cast.getObject());
			int hp = monster.damaged();
			if(hp == 0){
				cast.setObject(null);
			}
		}
	}
	
}
