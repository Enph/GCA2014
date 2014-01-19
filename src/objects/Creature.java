package objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Creature extends Obj{

	private int health;
	private String name;
	private int textureIndex;

	public Creature(String name, int textureIndex, int health){
		super(name,textureIndex);
		this.name = name;
		this.textureIndex = textureIndex;
		this.health = health;
	}

	public int damaged(){
		health--;
		if(health == 0){
			return 0;
		}
		return health;
	}

	@Override
	public void onStep(Square on, Panel panel) {
		// TODO Auto-generated method stub
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
