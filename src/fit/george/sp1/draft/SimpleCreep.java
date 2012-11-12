package fit.george.sp1.draft;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class SimpleCreep extends AnimatedSprite{

	private int healthPoint;
	private boolean isAlive;
	
	
//	public static SimpleCreep instance;
	
	public SimpleCreep( final float centerX, final float centerY, float pWidth, float pHeight, 
			ITiledTextureRegion pPlayerTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ) {
		
		super (centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.isAlive = true;
//		instance = this;
		
	}
	
	protected void setInitialHealthPoint(int healtPoint){
		this.healthPoint = healtPoint;
	}
	
}
