package com.example.gca2014;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	/** The OpenGL view */
	public static Panel glSurfaceView;
    public static GLRenderer mRenderer;
    MediaPlayer mplayer;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        // requesting to turn the title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Initiate the Open GL view and
        // create an instance with this activity
		 
		glSurfaceView = new Panel(this);
		mRenderer=new GLRenderer(this);
		
        // set our renderer to be the main renderer with
        // the current activity context
       
        glSurfaceView.setRenderer(mRenderer);
        setContentView(glSurfaceView);
        //mplayer=MediaPlayer.create(this, R.raw.tetris);
        //mplayer.start();
    }

    public void onDestroy(){
        
        glSurfaceView.running=false;
        //mplayer.release();
        super.onDestroy();
    }
	/** Remember to resume the glSurface  */
	@Override
	protected void onResume() {
		super.onResume();
		glSurfaceView.onResume();
	}

	/** Also pause the glSurface  */
	@Override
	protected void onPause() {
		super.onPause();
		glSurfaceView.onPause();
	}
	
	
	
	public void start()
	{
		Looper.prepare();
		mplayer.start();
	}
	
	
	public void changemusic()
	{
		
	}
}
