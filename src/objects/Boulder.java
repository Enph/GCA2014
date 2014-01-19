package objects;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Boulder extends Obj{

	public Boulder() {
		super("Boulder", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onStep(Square on, Panel panel) {
		// TODO Auto-generated method stub
		boolean moved = false;
		Player lyden = panel.getLyden();
		if(lyden.getFacing()==1){
			this.move(0, 1, panel);
			if(on.getObject()==null){
				moved = true;
			}
		}
		else if(lyden.getFacing()==2){
			this.move(1, 0, panel);
			if(on.getObject()==null){
				moved = true;
			}
		}
		else if(lyden.getFacing()==3){
			this.move(0, -1, panel);
			if(on.getObject()==null){
				moved = true;
			}
		}
		else{
			this.move(-1, 0, panel);
			if(on.getObject()==null){
				moved = true;
			}
		}
		if(moved == false){
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
		
	}
	
	public boolean breakable(){
		return true;
	}

}
