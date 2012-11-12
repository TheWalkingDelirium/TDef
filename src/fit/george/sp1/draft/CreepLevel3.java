package fit.george.sp1.draft;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CreepLevel3 extends SimpleCreep{

	public CreepLevel3(float centerX, float centerY, float pWidth,
			float pHeight, ITiledTextureRegion pPlayerTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		
		super(centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.setInitialHealthPoint(5000);
	}
	

}
