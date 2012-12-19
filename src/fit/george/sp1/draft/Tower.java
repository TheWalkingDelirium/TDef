package fit.george.sp1.draft;

import java.util.Random;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import android.util.Log;


/**
 * Class <code>Tower</code> represents defensive towers in the game. Inheritor of AnimatedSprite.
 * @author Radmir Usmanov
 *
 */
public class Tower extends AnimatedSprite {


	/**
	 * <code>randomGenerator</code>: generator of random numbers.
	 */
	private static Random randomGenerator = new Random();
	
	

	
	/**
	 * <code>Health</code>: tower's health.
	 */
	private int health = 100;
	
	
	/**
	 * <code>Distance</code>: tower's distance.
	 */
	protected int distance = 100;
	
	
	/**
	 * <code>Speed</code>: tower's speed.
	 */
	private int speed = 1; 
	
	
	/**
	 * <code>Damage_low</code>: tower's lowest damage.
	 */
	protected int damage_low = 5;
	
	
	/**
	 * <code>Damage_high</code>: tower's highest damage.
	 */
	protected int damage_high = 10;
	
	
	
	
	/**
	 * <code>Instance</code>: tower's pointer.
	 */
	private Tower instance;
	
	
	
	/**
	 * <code>Victim_number</code>: tower's victim number
	 */
	private int victim_number;
	
	
	/**
	 * <code>Victim</code>: is tower's victim chosen
	 */
	private boolean victim = false;
	

	
	protected Timer attackTimer;
	protected float attackRate = 0.2f;
	protected int animation_speed = 100;
	private Text text;
	private Sprite sellIcon, towerUpgradeDistance, towerUpgradeDamage, towerCancel; //icon sprites
	private boolean first = false;
	protected RangeCircle pRangeCircle; //tower range circle
	protected int totalCost; //total tower's price with all updates
	private float x; //X coordinate of tower
	private float y; //Y coordinate of tower
	
	
	protected int [][] upgradeDamagePrice = {{50, 2, 3}, {50, 2, 3}, {50, 2, 3}, {50, 2, 3}}; //price, low damage, high damage
	protected int [][] upgradeDistancePrice = {{50, 25}, {50, 25}, {50, 25}, {50, 25}}; //price, distance
	protected int MAX_DAMAGE_LEVELS;
	protected int MAX_DISTANCE_LEVELS;
	private int curDamageLevel = -1; //current damage level of tower
	private int curDistanceLevel = -1; //current distance level of tower
	

	
	
	
	
	
	/**
	 * Detach tower from the game scene.
	 */
	private void detach() {
		
		uncheck();
		instance.setIgnoreUpdate(true);
		instance.setVisible(false);
		game_Scene.game_instance.unregisterTouchArea(instance);
		game_Scene.game_instance.detachChild(instance);
		Matrix.matrix[((int) (y / 60)) + 1][((int) (x / 60)) + 1] = Constants.EMPTY;
		TowerObserver.list.remove(instance);
		instance.dispose();
		sellIcon.dispose();
		towerUpgradeDistance.dispose();
		towerUpgradeDamage.dispose();
		towerCancel.dispose();
		pRangeCircle.remove();
		text.setVisible(false);
		game_Scene.game_instance.detachChild(text);
		text.dispose();
	}
	
	
	
	/**
	 * Check tower.
	 */
	private void check() {
		
		game_Scene.game_instance.attachChild(sellIcon);
		game_Scene.game_instance.registerTouchArea(sellIcon);
		sellIcon.setVisible(true);
		
		
		game_Scene.game_instance.attachChild(towerUpgradeDistance);
		game_Scene.game_instance.registerTouchArea(towerUpgradeDistance);
		towerUpgradeDistance.setVisible(true);


		game_Scene.game_instance.attachChild(towerUpgradeDamage);
		game_Scene.game_instance.registerTouchArea(towerUpgradeDamage);
		towerUpgradeDamage.setVisible(true);

		
		game_Scene.game_instance.attachChild(towerCancel);
		game_Scene.game_instance.registerTouchArea(towerCancel);
		towerCancel.setVisible(true);

		
		game_Scene.game_instance.currentTower = this;
		pRangeCircle.setVisible(true);
		text.setVisible(true);
		

	}
	
	

	/**
	 * Uncheck tower.
	 */
	public void uncheck() {
		
		sellIcon.setVisible(false);
		game_Scene.game_instance.unregisterTouchArea(sellIcon);
		game_Scene.game_instance.detachChild(sellIcon);
		
		
		towerUpgradeDistance.setVisible(false);
		game_Scene.game_instance.unregisterTouchArea(towerUpgradeDistance);
		game_Scene.game_instance.detachChild(towerUpgradeDistance);
		
		
		towerUpgradeDamage.setVisible(false);
		game_Scene.game_instance.unregisterTouchArea(towerUpgradeDamage);
		game_Scene.game_instance.detachChild(towerUpgradeDamage);
		
		
		towerCancel.setVisible(false);
		game_Scene.game_instance.unregisterTouchArea(towerCancel);
		game_Scene.game_instance.detachChild(towerCancel);
		
		
		game_Scene.game_instance.currentTower = null;
		pRangeCircle.setVisible(false);
		text.setVisible(false);
		
	}

	
	
	@Override
	public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		
		if( game_Scene.game_instance.isRunning ) {
			if (pSceneTouchEvent.isActionUp() && first == true) {
				
				if(game_Scene.game_instance.currentTower == null && game_Scene.isMenuOpen == false) {
					check();
					game_Scene.game_instance.currentTower = this;
				}
				
			}
			
			first = true;
			
		}		
		return true;
	}
	

	
	
	/**
	 * Gets tower's price.
	 *
	 * @return
	 * 	<code>integer</code>: tower's price
	 */
	/*public static int GetPrice() {
		return price;
	}*/
	
	
	
	
	/**
	 * Upgrade tower damage.
	 * 
	 * @return
	 * 	<code>boolean</code>: true - successful upgrade; false - unsuccessful upgrade
	 */
	protected boolean UpgradeDamage()
	{
	
		if(curDamageLevel >= MAX_DAMAGE_LEVELS - 1) return false;
		else {
			
			if(game_Scene.game_instance.money.GetMoney() - upgradeDamagePrice[curDamageLevel + 1][0] >= 0) {
				
				curDamageLevel++;
				damage_low += upgradeDamagePrice[curDamageLevel][1];
				damage_high += upgradeDamagePrice[curDamageLevel][2];
				game_Scene.game_instance.money.DeductMoney(upgradeDamagePrice[curDamageLevel][0]);
				totalCost += upgradeDamagePrice[curDamageLevel][0];
				text.setText("Dmg: " + Integer.toString(damage_low) + "-" + Integer.toString(damage_high));
				return true;
				
			} else return false;
			
		}
	
	}
	
	
	
	
	/**
	 * Upgrade tower distance.
	 * 
	 * @return
	 * 	<code>boolean</code>: true - successful upgrade; false - unsuccessful upgrade
	 */
	private boolean UpgradeDistance()
	{
	
		if(curDistanceLevel >= MAX_DISTANCE_LEVELS - 1) return false;
		else {
			
			if(game_Scene.game_instance.money.GetMoney() - upgradeDistancePrice[curDistanceLevel + 1][0] >= 0) {
				
				curDistanceLevel++;
				game_Scene.game_instance.money.DeductMoney(upgradeDistancePrice[curDistanceLevel][0]);
				distance += upgradeDistancePrice[curDistanceLevel][1];
				totalCost += upgradeDistancePrice[curDistanceLevel][0];
				pRangeCircle.calculateCircle(distance);
				return true;
				
			} else return false;
			
		}
	
	}
	
	
	
	
	/**
	 * Sell tower.
	 */
	private void sellTower()
	{
	
		game_Scene.game_instance.money.AddMoney(totalCost / 2);
		instance.detach();
	
	}
	
	
	
	
	/**
	 * Method called on tower cancel icon click.
	 */
	private void towerCancel()
	{
		
		uncheck();
		//game_Scene.game_instance.currentTower = null;
	
	}	
	
	
	
	
	/**
	 * Generate random damage between damage_low and damage_high.
	 */
	private int randomDamage() {
		return randomGenerator.nextInt(this.damage_high) + this.damage_low;
	}
	
	
	

	
	private float creepDistance(int num) {
		
		float creep_x, creep_y, tower_x, tower_y, len;
		
		//synchronized(instance) {
				
			if(num < Creep_generator.creeps_alive.size()) {
				creep_x = Creep_generator.creeps_alive.get(num).getX() + 60 / 2;
				creep_y = Creep_generator.creeps_alive.get(num).getY() + 60 / 2;
				tower_x = instance.getX() + 60 / 2;
				tower_y = instance.getY() + 60 / 2;
				len = (creep_x - tower_x) * (creep_x - tower_x) + (creep_y - tower_y) * (creep_y - tower_y);
			} else len = -1;
		
		//}
		
		return len;
	}
	
	
	
	
	private int findVictim() {
		
		float d;
		for(int i = 0; i < Creep_generator.creeps_alive.size(); i++) {
					
			d = instance.creepDistance(i);
			if((distance * distance >= d) && (d != -1)) {
				victim = true;
				instance.StartFire();
				return i; //victim found
			}
		
		}
		
		return -1; //didn't find any victim
		
	}
	
	
	
	
	private void attackCreep(int num) {
		
		
		//synchronized(instance) {
			
			if(num < Creep_generator.creeps_alive.size()) {
			
			
				Creep_generator.creeps_alive.get(num).damageCreep(instance.randomDamage());
				if(Creep_generator.creeps_alive.get(num).isAlive() == false) {
					Creep_generator.creeps_alive.remove(num);
					victim = false;
					attackTimer.pause();
					instance.StopFire();
				}
			
			
			} else {
				victim = false;
				attackTimer.pause();
				instance.StopFire();
			}
	    	
			//Log.d("Health creep ", Integer.toString(num) + ": " + Integer.toString(Creep_generator.creeps_alive.get(num).getHealth()));
    	
		//}
		
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
		x = pX;
		y = pY;
		
		instance = this;
		
		MAX_DAMAGE_LEVELS = upgradeDamagePrice.length;
		MAX_DISTANCE_LEVELS = upgradeDistancePrice.length;
		
		
/*
		int delta_x = 0, delta_y = 0;
		
		if(((int)(y + 60 / 2)) - distance < 0) delta_y = distance - ((int)(y + 60 / 2));
		if(((int)(x + 60 / 2)) - distance < 0) delta_x = distance - ((int)(x + 60 / 2));
		
		if(((int)(x + 60 / 2)) + distance > 800) delta_x = (-1) * (((int)(x + 60 / 2)) + distance - 800);
		if(((int)(y + 60 / 2)) + distance > 480) delta_y = (-1) * (((int)(y + 60 / 2)) + distance - 480);
		
		pX += delta_x;
		pY += delta_y;
*/
		
		
		//Tower range circle
		pRangeCircle = new RangeCircle(x + 60 / 2, y + 60 / 2, distance, 40, Color.WHITE);
		
		
		
		
		text = new Text(pX, pY - 10, MainActivity.instance.towerFont, "Dmg: " + Integer.toString(damage_low) + "-" + Integer.toString(damage_high), 
				new TextOptions(HorizontalAlign.CENTER), MainActivity.instance.getVertexBufferObjectManager());
		game_Scene.game_instance.attachChild(text);
		text.setZIndex(4);
		text.setVisible(false);
		
		
		
		//Upgrade damage icon
		towerUpgradeDamage = new Sprite(pX - 60, pY - 60, MainActivity.instance.mTextureRegionUpgradeDamage, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					if(UpgradeDamage()) Log.d("Upgrade: ", "upgrade damage successful");
					else Log.d("Upgrade: ", "upgrade damage unsuccessful");
					
				}
				return true;
			}
		};
		towerUpgradeDamage.setScale(1.0f);
		towerUpgradeDamage.setZIndex(4);
		
		
		
		
		//Upgrade distance icon
		towerUpgradeDistance = new Sprite(pX + 60, pY - 60, MainActivity.instance.mTextureRegionUpgradeDistance, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
		
					if(UpgradeDistance()) Log.d("Upgrade: ", "upgrade distance successful");
					else Log.d("Upgrade: ", "upgrade distance unsuccessful");
					
				}
				return true;
			}
		};
		towerUpgradeDistance.setScale(1.0f);
		towerUpgradeDistance.setZIndex(4);
			
		
		
		
		//Sell tower icon
		sellIcon = new Sprite(pX - 60, pY + 60, MainActivity.instance.mTextureRegionSellIcon, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					sellTower();
					
				}
				return true;
			}
		};
		sellIcon.setScale(0.8f);
		sellIcon.setZIndex(4);
		
		
		
		
		//Tower cancel icon
		towerCancel = new Sprite(pX + 60, pY + 60, MainActivity.instance.mTextureRegionCancel, MainActivity.instance.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionUp()) {
					
					towerCancel();
					
				}
				return true;
			}
		};
		towerCancel.setScale(0.8f);
		towerCancel.setZIndex(4);
		

		
		
		//Tower attack
		attackTimer = new Timer(attackRate, true, new ITimerCallback() {        
	        public void onTimePassed(final TimerHandler pTimerHandler)
	        {
	        	attackCreep(victim_number);
        	}    
		});
		this.registerUpdateHandler(attackTimer);
        
        
        
        
		//this.onUpdate(2000f);
		
		
		this.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() { }

			@Override
			public void onUpdate(float pSecondsElapsed) {
			
				//Log.d("OnUpdate", "OnUpdate!!!!!!!!!!!!!!!!!!!!!!!!!!");
				
				float dis;
				//game_Scene.game_instance.sortChildren();
				
				if(victim == false) {
					victim_number = findVictim(); //find victim and store it in victim_number
				} else {
					dis = instance.creepDistance(victim_number);
					if((distance * distance >= dis) && (dis != -1)) { //victim in a circle
						attackTimer.resume();
					} else { //victim not in a circle
						victim = false;
						attackTimer.pause();
						instance.StopFire();
					}
				}
			
			}
		
		});
			
			
	}

	
	
	
	
	/**
	 * Starts tower's fire.
	 */
	public void StartFire()
	{
		this.animate(animation_speed);
	}
	
	
	
	
	/**
	 * Stops tower's fire.
	 */
	public void StopFire()
	{
		this.stopAnimation(0);
	}
	
	
	

}
