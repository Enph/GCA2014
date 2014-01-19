package objects;

import javax.microedition.khronos.opengles.GL10;

import spells.Spell;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Portal extends Obj {
	private Spell spell;
	public Portal(Spell spell){
		super("Portal",7);
		spell = spell;
	}
	
	@Override
	public void onStep(Square on, Panel panel) {
		if(spell != null){
			panel.getLyden().getSpellbook().add(spell);
		}
		panel.mazes.add(panel.maze);
		panel.maze.clear();
		panel.reputPlayer(4,2);
		panel.run();
		
	}
	
}
