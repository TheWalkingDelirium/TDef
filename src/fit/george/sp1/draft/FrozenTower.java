package fit.george.sp1.draft;


import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class FrozenTower extends Tower {

	
	private static int price = 200;
		
	public static int GetPrice() {
		return price;
	}
	
	public FrozenTower(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {

		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		totalCost = price;

		int [][] damage_levels = {{50, 2, 3}, {60, 2, 3}, {90, 2, 3}, {250, 5, 8}};
		int [][] distance_levels = {{50, 10}, {50, 10}, {50, 10}, {50, 10}};
		
		upgradeDamagePrice = damage_levels;
		upgradeDistancePrice = distance_levels;
		
		MAX_DAMAGE_LEVELS = upgradeDamagePrice.length;
		MAX_DISTANCE_LEVELS = upgradeDistancePrice.length;
		
		damage_low = 12;
		damage_high = 17;
		animation_speed = 100;
		attackRate = 0.5f;
		attackTimer.setTimerSeconds(attackRate);
		
		distance = 100;
		pRangeCircle.calculateCircle(distance);
		
		
	}
	
	 

}
