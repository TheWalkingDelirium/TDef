package fit.george.sp1.draft;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class CreepLevel2 extends SimpleCreep {

	public static CreepLevel2 instance;
	
	public CreepLevel2(float centerX, float centerY, float pWidth,
			float pHeight, ITiledTextureRegion pPlayerTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager)  {
		
		super(centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.setInitialHealthPoint(1000);
		
		instance = this;
	}
	

}
