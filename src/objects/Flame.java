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
		Player lyden = panel.getLyden();
		if(lyden.getX()==on.getX() && lyden.getY()==on.getY()){
			lyden.loseHealth();
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

	
	
}
