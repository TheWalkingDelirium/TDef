package fit.george.sp1.draft;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.PathModifier;
import org.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.andengine.entity.modifier.PathModifier.Path;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


/**
 * Class <code>Creep_octopus</code> extends by class <code>SimpleCreep</code>. 
 * Represents the creep. 
 * @author Trushin Artyom
 *  
 */

public class Creep_octopus extends SimpleCreep{

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
	public Creep_octopus(float centerX, float centerY, float pWidth,
			float pHeight, ITiledTextureRegion pPlayerTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, int healthPoint, float duration) {
		
		super(centerX, centerY, pWidth, pHeight, pPlayerTextureRegion, pVertexBufferObjectManager);
		this.setInitialHealthPoint(healthPoint);
		this.pDuration = duration;
		this.setInitialPrice(40);
		
		this.animate(100);
		
		final Path path = Matrix.getPath(0, 0);
		
		
		
		this.registerEntityModifier(new LoopEntityModifier(new PathModifier(this.pDuration, path, null, new IPathModifierListener() {
			
			
			
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {
			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
	
				
			}

			
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				detach();
				Castle.castle_instance.Attacked();
				}
		})));
				
		this.hpAnimator.registerEntityModifier(new LoopEntityModifier(new PathModifier(this.pDuration, path, null, new IPathModifierListener() {
			public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity) {

			}

			public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex) {
			}

			
			public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity) {
				}
		})));
		
		
		
		
		game_Scene.game_instance.attachChild(this);
		game_Scene.game_instance.attachChild(this.hpAnimator);
		
	}
	
	
	
}