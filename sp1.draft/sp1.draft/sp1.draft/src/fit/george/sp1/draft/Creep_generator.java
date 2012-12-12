package fit.george.sp1.draft;


import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

/**
 * Class <code>Creep_generator</code> extends by class <code>Sprite</code>. 
 * Represents the generator of creeps. 
 * @author Trushin Artyom 
 *  
 */

public class Creep_generator extends Sprite {
	public SimpleCreep [] creeps;
	//public static List<SimpleCreep> creeps_alive;
	public static TList creeps_alive;
	boolean wave_active;
	int actual_wave, str;
	public static int count; 
	

	/**
	 * Constructor of the class. Creates new instance of the <code>Creep_generator</code> class.
	 * 
	 * @param pX - position on X
	 * @param pY - position on Y
	 * @param pTextureRegion - texture of castle
	 * @param pVertexBufferObjectManager - standard parameter
	 */
	
	public Creep_generator(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		
		count = 0; 
		//creeps_alive = new ArrayList<SimpleCreep>();
		creeps_alive = new TList();
		wave_active = false;
		actual_wave = 0;
		str = 0;
		
		this.onUpdate(4.5f);
		
		
		this.registerUpdateHandler( new IUpdateHandler(){
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if ( !wave_active ) {
					wave_active = true;
					//Log.d("Creep_generator", Integer.toString(str++) );
					if( actual_wave == 2) game_Scene.game_instance.onWin();
					else makeCreeps();					
				}
				else {
					
					if ( creeps_alive.isEmpty() ) {		
						//Log.d("Creep_generator", Integer.toString(str--) );
						wave_active = false;
					}
				}				
			}
		});
		
		
	}
	
	/**
	 * The method for generation wave of creeps. 
	 * 
	 */
	
	private void makeCreeps() {
		
		Log.d("atual_wave", Integer.toString( actual_wave ) );
		
		
		
		
		if ( actual_wave == 0 ) {
			creeps = new SimpleCreep[15];
			count = 15;
			creeps[0] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, MainActivity.instance.getVertexBufferObjectManager());
			creeps_alive.add( creeps[0] );
		}
		else if ( actual_wave == 1 ) {
			creeps = new SimpleCreep[7];
			count = 7;
			creeps[0] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture, MainActivity.instance.getVertexBufferObjectManager());
			creeps_alive.add( creeps[0] );
		}
		else if ( actual_wave == 2 ) {
			creeps = new SimpleCreep[5];
			count = 5;
			creeps[0] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus, MainActivity.instance.getVertexBufferObjectManager());
			creeps_alive.add( creeps[0] );
		}
		else {
			count = 12;
			creeps = new SimpleCreep[12];
			creeps[0] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus, MainActivity.instance.getVertexBufferObjectManager());
			creeps_alive.add( creeps[0] );
		}
		
		
		
		
		
		
		game_Scene.game_instance.registerUpdateHandler(new TimerHandler(1.3f, true, new ITimerCallback() {
			int a = 1;
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {				
				
				//synchronized(this) {
				
				if( a < Creep_generator.count )  {
					Log.d("atual_wave_timer", Integer.toString( actual_wave ) + " " + Integer.toString( a ) );
					if ( actual_wave == 0 ) creeps[a] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, MainActivity.instance.getVertexBufferObjectManager());
					else if ( actual_wave == 1 ) creeps[a] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture, MainActivity.instance.getVertexBufferObjectManager());
					else if ( actual_wave == 2 ) creeps[a] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus, MainActivity.instance.getVertexBufferObjectManager());
					else {
						if ( a%3 == 0 ) creeps[a] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, MainActivity.instance.getVertexBufferObjectManager());
						else if ( a%3 == 1 ) creeps[a] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture, MainActivity.instance.getVertexBufferObjectManager());
						else if ( a%3 == 2 ) creeps[a] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus, MainActivity.instance.getVertexBufferObjectManager());
					}
					creeps_alive.add( creeps[a++] );
					Log.d("List size generator: ", Integer.toString(creeps_alive.size()));
					
					if ( a == Creep_generator.count ) {
						creeps = null;
						actual_wave++;
						a = 100;
					}
				}	
				
				//}
				
			}
		}));
		
	}
	
	
	/**
	 * The method for remove dead creeps from alive creeps list 
	 * 
	 */

	public synchronized static void controlCreeps_alive() {
		for (int i = creeps_alive.size() - 1; i >= 0  ; i--) 
			if ( !creeps_alive.get(i).isAlive() ) creeps_alive.remove(i);  
		
		Log.d("size_list", Integer.toString( creeps_alive.size() ) );
	}

}
