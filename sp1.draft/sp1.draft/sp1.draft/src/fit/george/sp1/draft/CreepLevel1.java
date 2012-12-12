package fit.george.sp1.draft;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Class <code>CreepLevel1</code> represents the easiest creeps in the game.
 * @author Iluha
 */
public class CreepLevel1 extends SimpleCreep{

	public static CreepLevel1 instance;
	
	/**
	 * Constructor of the class. Creates new instance of the <code>SimpleCreep</code> class.
	 * 
	 * @param centerX - position on X
	 * @param centerY - position on Y
	 * @param pWidth - width of the animation in pixels
	 * @param pHeight - height of the animation in pixels
	 * @param pPlayerTextureRegion
	 * @param pVertexBufferObjectManager
	 */
	public CreepLevel1(float centerX, float centerY, float pWidth,
			float pHeight, ITiledTextureRegion pPlayerTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		
		super(centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.setInitialHealthPoint(100);
		
		this.setInitialPrice(20);
		
		instance = this;
		
		final Path path = Matrix.getPath(10, -10);
		
		this.registerEntityModifier(new LoopEntityModifier(new PathModifier(13, path, null, new IPathModifierListener() {
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
				if ( Matrix.getDirection(pWaypointIndex) == Constants.UP )  animate(new long[]{200, 200, 200}, 0, 2, true);
				if ( Matrix.getDirection(pWaypointIndex) == Constants.RIGHT )  animate(new long[]{200, 200, 200}, 3, 5, true);
				if ( Matrix.getDirection(pWaypointIndex) == Constants.DOWN )  animate(new long[]{200, 200, 200}, 6, 8, true);
			}

			
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				
				detach();
				Castle.castle_instance.Attacked();
				}
		})));
			
		game_Scene.game_instance.attachChild(this);
		
	}
	

	

}