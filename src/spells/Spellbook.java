package spells;

import java.util.ArrayList;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Spellbook extends ArrayList<Spell>{

	public Spellbook(Panel panel){
		this.panel = panel;
	}
	private Panel panel;
	private Spell selected = null;
	public void scroll(int y){
		for(Spell spell:this){
			spell.rotate(this, y);
		}
	}
	public void setSelected(Spell spell) {
		selected = spell;
	}
	public void cast(Square square, Panel panel){
		if(selected!=null){
			square.setVisible(true);
		}
	}
	
	@Override
	public boolean add(Spell spell){
		
		return super.add(spell);
	}
}
