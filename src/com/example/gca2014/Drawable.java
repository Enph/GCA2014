package com.example.gca2014;

import javax.microedition.khronos.opengles.GL10;

public interface Drawable {
	public void draw(GL10 gl);
	public int textureIndex();
	public int textureSize();
}
