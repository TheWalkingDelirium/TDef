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
	public static List<SimpleCreep> creeps_alive;
	boolean wave_active, make_wave_finish;
	int actual_wave, str;
	public static int count; 
	
	private int creeps_level1_health;
	private int creeps_level2_health;
	private int creeps_octopus_health;
	
	private float creeps_level1_duration;
	private float creeps_level2_duration;
	private float creeps_octopus_duration;
	
	

	
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
		creeps_alive = new ArrayList<SimpleCreep>();
		wave_active = false;
		make_wave_finish = false;
		actual_wave = 0;
		str = 0;
		
		
		
		creeps_level1_health = 120;
		creeps_level2_health = 200;
		creeps_octopus_health = 200;
		
		creeps_level1_duration = 14;
		creeps_level2_duration = 10;
		creeps_octopus_duration = 15;
		
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
					if( actual_wave == 30) game_Scene.game_instance.onWin();
					else makeCreeps();						
				}
				else {
					
					if ( creeps_alive.isEmpty() && make_wave_finish ) {		
						make_wave_finish = false;
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
			creeps[0] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, 
					MainActivity.instance.getVertexBufferObjectManager(), creeps_level1_health, creeps_level1_duration);
			creeps_alive.add( creeps[0] );
		}
		else if ( actual_wave == 1 ) {
			creeps = new SimpleCreep[7];
			count = 7;
			creeps[0] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture,
					MainActivity.instance.getVertexBufferObjectManager(), creeps_level2_health, creeps_level2_duration);
			creeps_alive.add( creeps[0] );
		}
		else if ( actual_wave == 2 ) {
			creeps = new SimpleCreep[5];
			count = 5;
			creeps[0] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus,
					MainActivity.instance.getVertexBufferObjectManager(), creeps_octopus_health, creeps_octopus_duration);
			creeps_alive.add( creeps[0] );
		}
		else {
			count = 12;
			creeps = new SimpleCreep[12];
			creeps[0] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, 
					MainActivity.instance.getVertexBufferObjectManager(), creeps_level1_health, creeps_level1_duration);
			creeps_alive.add( creeps[0] );
		}
		
		
		
		
		
		
		game_Scene.game_instance.registerUpdateHandler(new TimerHandler(1.3f, true, new ITimerCallback() {
			int a = 1;
			@Override
			public void onTimePassed(final TimerHandler pTimerHandler) {				
				
				//synchronized(this) {
				
				
				if( a < Creep_generator.count )  {
					Log.d("atual_wave_timer", Integer.toString( actual_wave ) + " " + Integer.toString( a ) );
					if ( actual_wave == 0 ) creeps[a] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, 
							MainActivity.instance.getVertexBufferObjectManager(), creeps_level1_health, creeps_level1_duration);
					
					else if ( actual_wave == 1 ) creeps[a] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture,
							MainActivity.instance.getVertexBufferObjectManager(), creeps_level2_health, creeps_level2_duration);
					
					else if ( actual_wave == 2 ) creeps[a] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus,
							MainActivity.instance.getVertexBufferObjectManager(), creeps_octopus_health, creeps_octopus_duration);
					
					else {
						if ( a%3 == 0 ) creeps[a] = new CreepLevel1(130, 0, 48, 64, MainActivity.instance.creepLevel1Texture, 
								MainActivity.instance.getVertexBufferObjectManager(), creeps_level1_health, creeps_level1_duration);
						
						else if ( a%3 == 1 ) creeps[a] = new CreepLevel2(130, 0, 48, 64, MainActivity.instance.creepLevel2Texture,
								MainActivity.instance.getVertexBufferObjectManager(), creeps_level2_health, creeps_level2_duration);
						
						else if ( a%3 == 2 ) creeps[a] = new Creep_octopus(120, 0, 60, 60 , MainActivity.instance.octopus,
								MainActivity.instance.getVertexBufferObjectManager(), creeps_octopus_health, creeps_octopus_duration);
						}
					
					
					creeps_alive.add( creeps[a++] );
					//Log.d("List size generator: ", Integer.toString(creeps_alive.size()));
					
					if ( a == Creep_generator.count ) {
						creeps = null;
						if( actual_wave == 0 ) {
							creeps_level1_duration -= 1.5f;
							creeps_level1_health += 50;							
						}
						else if( actual_wave == 1 ) {
							creeps_level2_duration -= 0.5f;
							creeps_level2_health += 50;							
						}
						else if( actual_wave == 2 ) {
							creeps_octopus_duration -= 0.5f;
							creeps_octopus_health += 50;							
						}
						else {
							if ( creeps_level1_duration > 7) creeps_level1_duration -= 0.5f;
							creeps_level1_health += 50;	
							
							if ( creeps_level2_duration > 6) creeps_level2_duration -= 0.5f;
							creeps_level2_health += 50;	
							
							if ( creeps_octopus_duration > 9) creeps_octopus_duration -= 0.5f;
							creeps_octopus_health += 50;	
						}
						
						actual_wave++;
						make_wave_finish = true;
						a = 100;
					}
				}	
				
				game_Scene.game_instance.sortChildren();
				
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
		
		//Log.d("size_list", Integer.toString( creeps_alive.size() ) );
	}

}
