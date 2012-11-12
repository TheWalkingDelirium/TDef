package fit.george.sp1.draft;


import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class NPC extends AnimatedSprite{
	
	public static NPC instance;
	
	public NPC( final float centerX, final float centerY, float pWidth, float pHeight, 
			ITiledTextureRegion pPlayerTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager ){
		
		super (centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		
		instance = this;
		
		final Path path = new Path(7).to(centerX, centerY).to(130, 340).to(370, 340).to(370, 100).to(490, 100).to(490, 280).to(620, 280);
		
		this.registerEntityModifier(new LoopEntityModifier(new PathModifier(30, path, null, new IPathModifierListener() {
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				switch(pWaypointIndex) {
					case 0:
						animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 1:
						animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 2:
						animate(new long[]{200, 200, 200}, 0, 2, true);
						break;
					case 3:
						animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
					case 4:
						animate(new long[]{200, 200, 200}, 6, 8, true);
						break;
					case 5:
						animate(new long[]{200, 200, 200}, 3, 5, true);
						break;
				}
			}

			
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				setPosition(centerX, centerY);
				}
		})));
			
	}
	
}
