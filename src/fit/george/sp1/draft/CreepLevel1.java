package fit.george.sp1.draft;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CreepLevel1 extends SimpleCreep{

	public static CreepLevel1 instance;
	
	public CreepLevel1(float centerX, float centerY, float pWidth,
			float pHeight, ITiledTextureRegion pPlayerTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		
		super(centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.setInitialHealthPoint(100);
		
		instance = this;
	}

}
