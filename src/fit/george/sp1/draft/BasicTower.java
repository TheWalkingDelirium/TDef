package fit.george.sp1.draft;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class BasicTower extends Tower {

	private static int price = 100;
	
	public static int GetPrice() {
		return price;
	}
	
	
	public BasicTower(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {

		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);
		totalCost = price;

		int [][] damage_levels = {{50, 2, 3}, {70, 2, 3}, {90, 2, 3}, {100, 2, 3}};
		int [][] distance_levels = {{50, 25}, {50, 25}, {50, 25}, {50, 25}};
		
		upgradeDamagePrice = damage_levels;
		upgradeDistancePrice = distance_levels;
		
		MAX_DAMAGE_LEVELS = upgradeDamagePrice.length;
		MAX_DISTANCE_LEVELS = upgradeDistancePrice.length;
		
		damage_low = 7;
		damage_high = 9;
		animation_speed = 100;
		attackRate = 0.5f;
		attackTimer.setTimerSeconds(attackRate);
		
		distance = 100;
		pRangeCircle.calculateCircle(distance);
		
	}

}
