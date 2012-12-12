package fit.george.sp1.draft;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * @author Iluha
 *
 * Class <code>SimpleCreep</code> represents the base for the next creep classes.
 */

public class SimpleCreep extends AnimatedSprite{

	private int healthPoint;
	private boolean isAlive;
	
	private int price;
	
	
//	public static SimpleCreep instance;
	
	
	/**
	 * Constructor of the class. Creates new instance of the <code>SimpleCreep</code> class.
	 * 
	 * @param centerX - position on X
	 * @param centerY - position on Y
	 * @param pWidth - width of the animation in pixels
	 * @param pHeight - height of the animation in pixels
	 * @param pPlayerTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	
	public SimpleCreep( final float centerX, final float centerY, float pWidth, float pHeight, 
			ITiledTextureRegion pPlayerTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		
		super (centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.isAlive = true;
//		instance = this;
		
	}
	
	
	/**
	 * Sets initial heath points of the creep.
	 * 
	 * @param <code>healthPoint</code> - initial health points
	 */
	
	protected void setInitialHealthPoint(int healthPoint){
		this.healthPoint = healthPoint;
	}
	
	
	protected void setInitialPrice(int price){
		this.price = price;
	}
	
	
	/**
	 * Decreases creep's heath points.
	 * 
	 * @param <code>damage</code> - value of the damage.
	 */
	
	public void damageCreep(int damage){
		this.healthPoint -= damage;
		
		if (this.healthPoint <= 0 ) {
			Money.instance.AddMoney(price);
			detach();			
		}
			
	}
	
	
	/**
	 * Checks creep's status.
	 * 
	 * @return
	 * 	<code>true</code> if creep is alive
	 * 	<code>false</code> if creep is not alive
	 */
	
	public boolean isAlive(){
		return this.isAlive;
	}
	
	
	
	public void detach() {
		this.isAlive = false;
		this.clearEntityModifiers();		
		this.setVisible(false);
		//this.setIgnoreUpdate(true);
		//this.clearUpdateHandlers();
	}
}
