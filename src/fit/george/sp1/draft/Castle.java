package fit.george.sp1.draft;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;


/**
 * Class <code>Castle</code> extends by class <code>Sprite</code>. 
 * Represents the castle, end point for creeps. 
 * @author Trushin Artyom
 *  
 */
public class Castle extends Sprite {
	
	private static int health = 5;
	public static Castle castle_instance;
	
	
	/**
	 * Get castles health.
	 * 
	 * @return
	 * 	<code>Integer</code> of castles health
	 */
	
	public int GetHealth() {
		return health;
	}
	
	
	
	
	/**
	 * Decreases castle's heath points.
	 * 
	 */
	
	public void Attacked() {
		Creep_generator.controlCreeps_alive();
		
		health--;
		if( !isAlive() ) game_Scene.game_instance.onGameOver();
		String str = "";
		str += Integer.toString(health);
		Log.d("Castle", str);
	}
	
	
	/**
	 * Checks castle's status.
	 * 
	 * @return
	 * 	<code>true</code> if castle is alive
	 * 	<code>false</code> if castle is not alive
	 */
	
	
	public boolean isAlive() {
		if ( health > 0 ) return true;
		else return false;
	}
	
	
	/**
	 * Constructor of the class. Creates new instance of the <code>Castle</code> class.
	 * 
	 * @param pX - position on X
	 * @param pY - position on Y
	 * @param pTextureRegion - texture of castle
	 * @param pVertexBufferObjectManager - standard parameter
	 */

	public Castle(float pX, float pY, 
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) 
	{		
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		castle_instance = this;
	}
	
}
