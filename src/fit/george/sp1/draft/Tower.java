package fit.george.sp1.draft;

import java.util.Random;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;


/**
 * Class <code>Tower</code> represents defensive towers in the game. Inheritor of AnimatedSprite.
 * @author Radmir Usmanov
 *
 * 
 */
public class Tower extends AnimatedSprite {


	
	private static Random randomGenerator = new Random();
	
	/**
	 * <code>Price</code>: tower's price.
	 */
	private static int price = 100;
	
	
	/**
	 * <code>Health</code>: tower's health.
	 */
	private int health = 100;
	
	
	/**
	 * <code>Distance</code>: tower's distance.
	 */
	private int distance = 100; //Tower distance
	
	
	/**
	 * <code>Speed</code>: tower's speed.
	 */
	private int speed = 1; 
	
	
	/**
	 * <code>Damage_low</code>: tower's lowest damage.
	 */
	private int damage_low = 5;
	
	
	/**
	 * <code>Damage_high</code>: tower's highest damage.
	 */
	private int damage_high = 10;
	
	
	/**
	 * <code>Delay</code>: tower's delay.
	 */
	private static int delay = 5;
	
	
	
	/**
	 * <code>Instance</code>: tower's pointer.
	 */
	private Tower instance;
	
	/**
	 * <code>Animation</code>: is tower animation run.
	 * 
	 */
	private boolean animation;
	
	/**
	 * <code>Victim_number</code>: tower's victim number
	 * 
	 */
	private int victim_number;
	
	/**
	 * <code>Victim</code>: is tower's victim chosen
	 * 
	 */
	private boolean victim = false; //Is victim chosen
	

	
	
	/**
	 * Gets tower's price.
	 *
	 * @return
	 * 	<code>integer</code>: tower's price
	 */
	public static int GetPrice() {
		return price;
	}
	
	
	/**
	 * Upgrade tower.
	 *
	 */
	public void Upgrade()
	{
		this.speed += 1;
		this.damage_low += 5;
		this.damage_high += 5;
	}
	
	
	
	private int randomDamage() {
		return randomGenerator.nextInt(this.damage_high) + this.damage_low;
	}
	
	
	/**
	 * Constructor of the class. Creates new instance of the <code>Tower</code> class.
	 * 
	 * @param pX - Tower's X coordinate
	 * @param pY - Tower's Y coordinate
	 * @param pTiledTextureRegion
	 * @param vertexBufferObjectManager
	 */
	public Tower(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
	
		
		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		instance = this;
		animation = false;
		

		
		
		this.registerUpdateHandler(new IUpdateHandler() {
			
			
			float creep_x;
			float creep_y;
			float tower_x;
			float tower_y;
			float len;
			float dis;
			SimpleCreep tmp;
			int k = 0;
			
			
			
			public float creepDistance(int num) {
				
				synchronized(this) {
						
					if(num < Creep_generator.creeps_alive.size()) {
						creep_x = Creep_generator.creeps_alive.get(num).getX();
						creep_y = Creep_generator.creeps_alive.get(num).getY();
						tower_x = instance.getX();
						tower_y = instance.getY();
						len = (creep_x - tower_x) * (creep_x - tower_x) + (creep_y - tower_y) * (creep_y - tower_y);
					} else len = -1;
				
				}
				
				
				return len;
			}
			
			
			@Override
			public void reset() { }

			@Override
			public void onUpdate(final float pSecondsElapsed) {
			
				
				//find victim
				if(victim == false) {
					
						
						for(int i = 0; i < Creep_generator.creeps_alive.size(); i++) {
						
							dis = creepDistance(i);
							if((distance * distance >= dis) && (dis != -1)) {
								victim_number = i;
								victim = true;
								break;
							} else if(dis == -1) break;
							
						}
						
					
					
				} else {

					dis = creepDistance(victim_number);
					if((distance * distance >= dis) && (dis != -1)) {
						
						
						if(animation == false) {
							instance.StartFire();
							animation = true;
						}
						
						
						synchronized(this) {
							if(victim_number < Creep_generator.creeps_alive.size()) {
								
								if(++k == Tower.delay) {
									//Log.d("victim: ", Integer.toString(victim_number));
									Creep_generator.creeps_alive.get(victim_number).damageCreep(instance.randomDamage());
									k = 0;
								}
						
								if(Creep_generator.creeps_alive.get(victim_number).isAlive() == false) {
									//Log.d("victim: ", Integer.toString(victim_number));
									tmp = Creep_generator.creeps_alive.remove(victim_number);
									
									//Log.d("List size: ", Integer.toString(Creep_generator.creeps_alive.size()));
									victim = false;
									instance.StopFire();
									animation = false;
								}
								
								
							} else {
								victim = false;
								instance.StopFire();
								animation = false;
							}
						}
						
						
							
					} else {
						
						victim = false;
						instance.StopFire();
						animation = false;
						
					}
					
					
					
					

					
				}
				
				
				

				
				
			}
		
		});
			
			
	}

	
	
	
	
	
	
	
	/**
	 * Starts tower's fire.
	 *
	 */
	public void StartFire()
	{
		this.animate(100);
	}
	
	
	
	
	/**
	 * Stops tower's fire.
	 *
	 */
	public void StopFire()
	{
		this.stopAnimation(0);
	}
	
	
	

}
