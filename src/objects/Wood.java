package objects;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Wood extends Obj{

	public Wood(){
		super("wood",27);
	}

	@Override
	public void onStep(Square on, Panel panel) {
		Player lyden = panel.getLyden();
		if(lyden.getX()==on.getX() && lyden.getY()==on.getY()){
			//move back 1 depending on facing
			if(lyden.getFacing()==1){
				lyden.move(0,-1);
			}
			if(lyden.getFacing()==2){
				lyden.move(-1,0);
			}
			if(lyden.getFacing()==3){
				lyden.move(0,1);
			}
			else{
				lyden.move(1,0);
			}
		}
		
	}
	
	public boolean burnable(){
		return true;
	}
	
}
