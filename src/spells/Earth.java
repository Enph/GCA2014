package spells;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Earth extends Spell{

	public Earth(){
		super("Earth",10,10);
	}
	
	public void onClick(Square cast, Panel panel) {
		if(cast.getObject().getName()=="Flame"){
			cast.setObject(null);
		}
	}
	
}
