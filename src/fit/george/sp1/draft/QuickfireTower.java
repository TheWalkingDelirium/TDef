package fit.george.sp1.draft;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class QuickfireTower extends Tower {

	
	private static int price = 150;
	
	
	
	public static int GetPrice() {
		return price;
	}
	
	
	public QuickfireTower(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
		
		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		totalCost = price;
		
		int [][] damage_levels = {{50, 2, 3}, {70, 2, 3}, {90, 2, 3}, {110, 2, 3}};
		int [][] distance_levels = {{50, 10}, {50, 10}, {50, 10}, {50, 10}};
		
		upgradeDamagePrice = damage_levels;
		upgradeDistancePrice = distance_levels;
		
		MAX_DAMAGE_LEVELS = upgradeDamagePrice.length;
		MAX_DISTANCE_LEVELS = upgradeDistancePrice.length;
		
		
		damage_low = 5;
		damage_high = 7;
		animation_speed = 100;
		attackRate = 0.25f;
		attackTimer.setTimerSeconds(attackRate);
		
		distance = 150;
		pRangeCircle.calculateCircle(distance);
		
	}
	
	

}
