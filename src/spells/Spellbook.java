package spells;

import java.util.ArrayList;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Spellbook extends ArrayList<Spell>{

	private Spell selected = null;
	public void scroll(int y){
		if(y==0){
			return;
		}
		int diry = Math.abs(y)/y;
		for(int i=0;i!=y;i+=diry){
			selected = null;
			for(Spell spell:this){
				spell.rotate(this, diry);
			}
			if(selected==null){
				for(Spell spell:this){
					spell.rotate(this, -diry);
				}
				break;
			}
		}

	}
	public void unsafeScroll(int y){
		if(y==0){
			return;
		}
		int diry = Math.abs(y)/y;
		for(int i=0;i!=y;i+=diry){
			selected = null;
			for(Spell spell:this){
				spell.rotate(this, diry);
			}
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
		
		while(selected!=null){
			selected = null;
			for(Spell s:this){
				s.rotate(this, 1);
			}
		}
		Panel.context.mRenderer.addDrawable(spell);
		boolean result = super.add(spell);
		scroll(1);
		return result;
	}
}
