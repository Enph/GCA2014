package objects;

import javax.microedition.khronos.opengles.GL10;

import spells.Spell;

import com.example.gca2014.Panel;
import com.example.gca2014.Square;

public class Portal extends Obj {
	private Spell spell;
	private int to;
	private Portal dependant;
	private boolean active;
	private float red;
	private float green;
	private float blue;
	public Portal(Spell spell, int to, boolean active, Portal dependant, float red, float green, float blue){
		super("Portal",-1);
		this.spell = spell;
		this.to = to;
		this.active = active;
		this.dependant = dependant;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void onStep(Square on, Panel panel) {
		if(active){
			if(spell != null){
				panel.getLyden().getSpellbook().add(spell);
			}
			if(dependant!=null){
				dependant.activate();
			}
			panel.run(to);
		}
	}

	private void activate() {
		Panel.context.mRenderer.removeDrawable(this);
		active = true;
		Panel.context.mRenderer.addDrawable(this);
	}

	@Override
	public void draw(GL10 gl){
		if(active){
			gl.glColor4f(red, green, blue, 1f);
			super.draw(gl);
			gl.glColor4f(1f, 1f, 1f, 1f);
		}
		else {
			super.draw(gl);
		}
	}

	@Override
	public int textureIndex(){
		return active?7:5;
	}

}
