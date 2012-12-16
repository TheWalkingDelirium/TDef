package fit.george.sp1.draft;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

/**
 * @author Iluha
 *
 * Class <code>SimpleCreep</code> represents the base for the next creep classes.
 */

public class SimpleCreep extends AnimatedSprite{

	private int healthPoint;
	private boolean isAlive;
	public Rectangle hpAnimator;
	public float pDuration;
	
	private TimerHandler stun;
	
	private int price;
	
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
		
		this.hpAnimator = new Rectangle(centerX, centerY, 45, 10, pVertexBufferObjectManager);
		this.hpAnimator.setColor(Color.RED);
		this.hpAnimator.setVisible(false);

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
		this.hpAnimator.setVisible(true);
		
		this.hpAnimator.setWidth((this.hpAnimator.getWidth()*this.healthPoint)/(this.healthPoint + damage));
		if (this.healthPoint <= 0 ) {
			Money.instance.AddMoney(price);
			detach();			
		}
	}
	
	public void stun(float stunTime){

		
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
		this.hpAnimator.setVisible(false);
		this.hpAnimator.clearEntityModifiers();
		this.clearEntityModifiers();		
		this.setVisible(false);
	}
	
	
}
