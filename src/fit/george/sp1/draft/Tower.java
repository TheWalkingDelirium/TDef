package fit.george.sp1.draft;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Tower extends AnimatedSprite {

	
	private static int price = 100;
	private int health = 100;
	private static int distance = 10;
	private static int speed = 1;
	private static int strength = 1;
	
	
	
	public static int GetPrice() {
		return price;
	}
	
	
	
	
	public void Upgrade()
	{
		Tower.speed += 1;
		Tower.strength += 1;
	}
	
	
	
	
	public Tower(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vertexBufferObjectManager) {
	
		
		super(pX, pY, pTiledTextureRegion, vertexBufferObjectManager);

		
	}

	
	
	
	
	
	public void StartFire()
	{
		this.animate(100);
	}
	
	
	
	
	public void StopFire()
	{
		this.stopAnimation();
	}
	
	
	

}
