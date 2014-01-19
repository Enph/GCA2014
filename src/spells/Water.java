package spells;

import objects.Creature;
import objects.Player;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Water extends Spell{

	public Water(){
		super("Water",10,10);
	}
	
	@Override
	public void onClick(Square cast, Panel panel) {
		Player lyden = panel.getLyden();
		if(cast.getObject().getName()=="Flame"){
			cast.setObject(null);
		}
		if(cast.getObject().getName() == "Creature"){
			Creature monster = (Creature)(cast.getObject());
			if(lyden.getFacing()==1){
				monster.move(0, 1, panel);
			}
			else if(lyden.getFacing()==2){
				monster.move(1, 0, panel);		
			}
			else if(lyden.getFacing()==3){
				monster.move(0, -1, panel);
			}
			else{
				monster.move(-1, 0, panel);
			}
		}
	}
	
}
