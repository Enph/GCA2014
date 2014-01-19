package objects;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Flame extends Obj {
	
	public Flame(){
		super("Flame",41);
	}

	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStep(Square on, Panel panel) {
		Player loc = panel.getLyden();
		if(loc.getX()==on.getX() && loc.getY()==on.getY()){
			loc.loseHealth();
			//move back 1 depending on facing
			if(loc.getFacing()==1){
				loc.move(0,-1);
			}
			if(loc.getFacing()==2){
				loc.move(-1,0);
			}
			if(loc.getFacing()==3){
				loc.move(0,1);
			}
			else{
				loc.move(1,0);
			}
		}
	}

	
	
}
