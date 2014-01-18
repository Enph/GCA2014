package spells;

import javax.microedition.khronos.opengles.GL10;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Light extends Spell{

	public Light() {
		super("Light", 35, 36);
	}

	@Override
	public void onClick(Square cast, Panel panel) {
		cast.setVisible(true);
	}
	

}
